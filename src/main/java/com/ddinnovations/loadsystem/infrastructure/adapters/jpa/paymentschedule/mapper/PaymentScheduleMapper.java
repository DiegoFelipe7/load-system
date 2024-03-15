package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.paymentschedule.mapper;

import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.LoanEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.mapper.LoanMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.paymentschedule.PaymentScheduleEntity;

public class PaymentScheduleMapper {
    private PaymentScheduleMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static PaymentSchedule paymentScheduleDtoAPaymentSchedule(PaymentScheduleEntity paymentSchedule) {
        return PaymentSchedule.builder()
                .id(paymentSchedule.getId())
                .paymentDate(paymentSchedule.getPaymentDate())
                .amount(paymentSchedule.getAmount())
                .quotaNumber(paymentSchedule.getQuotaNumber())
                .paymentCycle(paymentSchedule.getPaymentCycle())
                .paymentStatus(paymentSchedule.getPaymentStatus())
                .searchKey(paymentSchedule.getSearchKey())
                .createdAt(paymentSchedule.getCreatedAt())
                .updatedAt(paymentSchedule.getUpdatedAt())
                .build();
    }

    public static PaymentScheduleEntity paymentScheduleAPaymentScheduleDto(PaymentSchedule paymentSchedule, LoanEntity loan ) {
        return PaymentScheduleEntity.builder()
                .id(paymentSchedule.getId())
                .paymentDate(paymentSchedule.getPaymentDate())
                .amount(paymentSchedule.getAmount())
                .quotaNumber(paymentSchedule.getQuotaNumber())
                .paymentCycle(paymentSchedule.getPaymentCycle())
                .loan(loan)
                .paymentStatus(paymentSchedule.getPaymentStatus())
                .searchKey(paymentSchedule.getSearchKey())
                .build();
    }
}
