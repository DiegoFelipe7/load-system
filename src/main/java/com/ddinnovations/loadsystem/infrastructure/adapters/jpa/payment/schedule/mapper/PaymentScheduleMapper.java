package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule.mapper;

import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentIndicatorsDto;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.LoanEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.mapper.LoanMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule.PaymentScheduleEntity;

import java.math.BigDecimal;

public class PaymentScheduleMapper {
    private PaymentScheduleMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static PaymentSchedule paymentScheduleDtoAPaymentSchedule(PaymentScheduleEntity paymentSchedule) {
        return PaymentSchedule.builder()
                .id(paymentSchedule.getId())
                .paymentDate(paymentSchedule.getPaymentDate())
                .paymentReference(paymentSchedule.getPaymentReference())
                .amount(paymentSchedule.getAmount())
                .balancePaid(paymentSchedule.getBalancePaid())
                .outstandingBalance(paymentSchedule.getOutstandingBalance())
                .quotaNumber(paymentSchedule.getQuotaNumber())
                .paymentCycle(paymentSchedule.getPaymentCycle())
                .paymentStatus(paymentSchedule.getPaymentStatus())
                .searchKey(paymentSchedule.getSearchKey())
                .createdAt(paymentSchedule.getCreatedAt())
                .updatedAt(paymentSchedule.getUpdatedAt())
                .build();
    }

    public static PaymentScheduleEntity paymentScheduleAPaymentScheduleDto(BigDecimal earnings, PaymentSchedule paymentSchedule, LoanEntity loan) {
        return PaymentScheduleEntity.builder()
                .id(paymentSchedule.getId())
                .earnings(earnings)
                .paymentDate(paymentSchedule.getPaymentDate())
                .paymentReference(paymentSchedule.getPaymentReference())
                .amount(paymentSchedule.getAmount())
                .quotaNumber(paymentSchedule.getQuotaNumber())
                .paymentCycle(paymentSchedule.getPaymentCycle())
                .loan(loan)
                .paymentStatus(paymentSchedule.getPaymentStatus())
                .searchKey(paymentSchedule.getSearchKey())
                .build();
    }


    public static PaymentSchedule paymentScheduleDTO(PaymentScheduleEntity paymentSchedule) {
        return PaymentSchedule.builder()
                .id(paymentSchedule.getId())
                .paymentDate(paymentSchedule.getPaymentDate())
                .paymentReference(paymentSchedule.getPaymentReference())
                .amount(paymentSchedule.getAmount())
                .balancePaid(paymentSchedule.getBalancePaid())
                .outstandingBalance(paymentSchedule.getOutstandingBalance())
                .loan(LoanMapper.loanDtoALoan(paymentSchedule.getLoan()))
                .quotaNumber(paymentSchedule.getQuotaNumber())
                .paymentCycle(paymentSchedule.getPaymentCycle())
                .paymentStatus(paymentSchedule.getPaymentStatus())
                .searchKey(paymentSchedule.getSearchKey())
                .createdAt(paymentSchedule.getCreatedAt())
                .updatedAt(paymentSchedule.getUpdatedAt())
                .build();
    }

    public static PaymentIndicatorsDto paymentIndicators(Object object) {
        if (object instanceof Object[] array) {
            BigDecimal totalBalance = (BigDecimal) array[0];
            BigDecimal raisedMoney = (BigDecimal) array[1];
            Long paymentsMade = ((Number) array[2]).longValue();
            Long overduePayments = ((Number) array[3]).longValue();
            return new PaymentIndicatorsDto(totalBalance, raisedMoney, paymentsMade, overduePayments);
        }
        throw new BusinessException(BusinessException.Type.ERROR_BD);

    }
}
