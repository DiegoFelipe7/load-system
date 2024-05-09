package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule;

import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.User;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentDTO;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentIndicatorsDto;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.repository.PaymentScheduleRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.AdapterOperations;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.GenerateDates;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.GeneratePaymentReference;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.LoanRepositoryAdapter;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule.mapper.PaymentScheduleMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.user.mapper.UserMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public class PaymentScheduleRepositoryAdapter extends AdapterOperations<PaymentSchedule, PaymentScheduleEntity, String, PaymentScheduleDtoRepository> implements PaymentScheduleRepository {
    private final LoanRepositoryAdapter loanRepositoryAdapter;

    protected PaymentScheduleRepositoryAdapter(PaymentScheduleDtoRepository repository, ObjectMapper mapper, LoanRepositoryAdapter loanRepositoryAdapter) {
        super(repository, mapper, d -> mapper.map(d, PaymentSchedule.PaymentScheduleBuilder.class).build());
        this.loanRepositoryAdapter = loanRepositoryAdapter;
    }


    @Override
    @Transactional
    public ResponseGlobal<PaymentSchedule> makePayment(String id, PaymentDTO paymentDTO, User user) {
        PaymentScheduleEntity paymentSchedule = this.getByIdPaymentSchedule(id);
        paymentSchedule.setInterests(paymentSchedule.getInterests());
        paymentSchedule.setAmount(paymentSchedule.getAmount().add(paymentSchedule.getAmount()));
        if (paymentSchedule.getPaymentStatus().equals(PaymentStatus.Pagado)) {
            throw new BusinessException(BusinessException.Type.PAYMENT_MADE);
        }
        if (Boolean.FALSE.equals(paymentDTO.isFullPayment())) {
            BigDecimal outstandingBalance = paymentSchedule.getAmount()
                    .subtract(paymentDTO.balance());

            paymentSchedule.setOutstandingBalance(outstandingBalance);
            getNextPayment(paymentSchedule.getPaymentReference(), outstandingBalance);
        }
        paymentSchedule.setBalancePaid(paymentDTO.isFullPayment() ? paymentSchedule.getAmount() : paymentDTO.balance());
        paymentSchedule.setPaymentStatus(PaymentStatus.Pagado);
        paymentSchedule.setUser(UserMapper.userLoginEntity(user));
        loanRepositoryAdapter.updatePaymentNumber(paymentSchedule.getLoan().getId());
        return new ResponseGlobal<>(PaymentScheduleMapper.paymentScheduleDTO(repository.save(paymentSchedule)));
    }

    @Override
    public ResponseGlobal<PaymentIndicatorsDto> paymentIndicators() {
        Object object = repository.getIndicators(GenerateDates.starDateFilter(), GenerateDates.endDateFilter());
        return new ResponseGlobal<>(PaymentScheduleMapper.paymentIndicators(object));
    }

    @Override
    @Scheduled(cron = "0 0 23 * * *", zone = "America/Santo_Domingo")
    public void updateStatusToLate() {
        repository.findAllByPaymentDate(GenerateDates.paymentDate())
                .forEach(ele -> {
                    ele.setPaymentStatus(PaymentStatus.Mora);
                    repository.save(ele);
                });

    }

    private PaymentScheduleEntity getByIdPaymentSchedule(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.INVALID_PAYMENT_REFERENCE));
    }

    private void getNextPayment(String paymentReference, BigDecimal outstandingBalance) {
        String reference = GeneratePaymentReference.reference(paymentReference);
        PaymentScheduleEntity paymentSchedule = repository.findByPaymentReference(reference)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.PAYMENT_INVALID));
        paymentSchedule.setAmount(paymentSchedule.getAmount().add(outstandingBalance));
        repository.save(paymentSchedule);
    }
}
