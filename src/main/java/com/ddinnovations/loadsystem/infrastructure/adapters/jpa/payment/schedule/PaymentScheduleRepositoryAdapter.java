package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule;

import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsPaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.response.Pagination;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import com.ddinnovations.loadsystem.domain.repository.PaymentScheduleRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.filters.PaymentScheduleSpecification;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.AdapterOperations;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.LoanRepositoryAdapter;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule.mapper.PaymentScheduleMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PaymentScheduleRepositoryAdapter extends AdapterOperations<PaymentSchedule, PaymentScheduleEntity, String, PaymentScheduleDtoRepository> implements PaymentScheduleRepository {
    private final LoanRepositoryAdapter loanRepositoryAdapter;

    protected PaymentScheduleRepositoryAdapter(PaymentScheduleDtoRepository repository, ObjectMapper mapper, LoanRepositoryAdapter loanRepositoryAdapter) {
        super(repository, mapper, d -> mapper.map(d, PaymentSchedule.PaymentScheduleBuilder.class).build());
        this.loanRepositoryAdapter = loanRepositoryAdapter;
    }


    @Override
    public ResponseGlobalPagination<List<PaymentSchedule>> findAllPaymentSchedule(ParamsPaymentSchedule paymentSchedule) {
        PaymentScheduleSpecification specification = new PaymentScheduleSpecification(paymentSchedule.getStartDate(), paymentSchedule.getFilterCriteriaText(), paymentSchedule.getPaymentCycle());
        PageRequest pages = PageRequest.of(paymentSchedule.getPage(), paymentSchedule.getLimit(), paymentSchedule.getSort());
        List<PaymentSchedule> loanApplications = repository.findAll(specification, pages)
                .stream()
                .map(PaymentScheduleMapper::paymentScheduleDTO)
                .toList();
        return new ResponseGlobalPagination<>(loanApplications, new Pagination(paymentSchedule.getPage(), paymentSchedule.getLimit(), ((int) repository.count())));
        //return  null;
    }

    @Override
    public ResponseGlobal<PaymentSchedule> findByIdPaymentSchedule(String id) {
        PaymentScheduleEntity paymentSchedule = this.getByIdPaymentSchedule(id);
        return new ResponseGlobal<>(PaymentScheduleMapper.paymentScheduleDtoAPaymentSchedule(paymentSchedule));
    }

    @Override
    @Transactional
    public ResponseGlobal<PaymentSchedule> makePayment(String id) {
        PaymentScheduleEntity paymentSchedule = this.getByIdPaymentSchedule(id);
        if (paymentSchedule.getPaymentStatus().equals(PaymentStatus.Pagado)) {
            throw new BusinessException(BusinessException.Type.PAYMENT_INVALID);
        }
        paymentSchedule.setPaymentStatus(PaymentStatus.Pagado);
        loanRepositoryAdapter.updatePaymentNumber(paymentSchedule.getLoan().getId());
        return new ResponseGlobal<>(PaymentScheduleMapper.paymentScheduleDtoAPaymentSchedule(repository.save(paymentSchedule)));
    }

    //TODO: CAMBIAR MENSAJE
    private PaymentScheduleEntity getByIdPaymentSchedule(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.ERROR_BD));
    }
}
