package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule;

import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentIndicatorsDto;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.repository.PaymentScheduleRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.AdapterOperations;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.GenerateDates;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.LoanRepositoryAdapter;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule.mapper.PaymentScheduleMapper;
import org.reactivecommons.utils.ObjectMapper;
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
    public ResponseGlobal<PaymentSchedule> makePayment(String id) {
        PaymentScheduleEntity paymentSchedule = this.getByIdPaymentSchedule(id);
        if (paymentSchedule.getPaymentStatus().equals(PaymentStatus.Pagado)) {
            throw new BusinessException(BusinessException.Type.PAYMENT_INVALID);
        }
        paymentSchedule.setPaymentStatus(PaymentStatus.Pagado);
        loanRepositoryAdapter.updatePaymentNumber(paymentSchedule.getLoan().getId());
        return new ResponseGlobal<>(PaymentScheduleMapper.paymentScheduleDTO(repository.save(paymentSchedule)));
    }

    @Override
    public ResponseGlobal<PaymentIndicatorsDto> paymentIndicators() {
        Object object = repository.getIndicators(GenerateDates.starDateFilter(), GenerateDates.endDateFilter());

        if (object instanceof Object[] array) {
            BigDecimal totalBalance = (BigDecimal) array[0];
            BigDecimal raisedMoney = (BigDecimal) array[1];
            Long paymentsMade = ((Number) array[2]).longValue();
            Long overduePayments = ((Number) array[3]).longValue();
            return new ResponseGlobal<>(new PaymentIndicatorsDto(totalBalance, raisedMoney, paymentsMade, overduePayments));
        }
        return new ResponseGlobal<>(new PaymentIndicatorsDto(BigDecimal.ZERO, BigDecimal.ZERO, 0L, 0L));

    }

    @Override
    @Scheduled(cron = "0 10 0 * * *", zone = "America/Santo_Domingo")
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
}
