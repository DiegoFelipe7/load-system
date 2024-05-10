package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.mapper;

import com.ddinnovations.loadsystem.domain.entity.Loan;
import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanIndicatorDTO;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanReportDto;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.mapper.ClientMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.LoanEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule.PaymentScheduleEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule.mapper.PaymentScheduleMapper;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class LoanMapper {

    private LoanMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Loan loanDtoALoan(LoanEntity loan) {
        return Loan.builder()
                .id(loan.getId())
                .amount(loan.getAmount())
                .paymentCycle(loan.getPaymentCycle())
                .interest(loan.getInterest())
                .numberOfPayments(loan.getNumberOfPayments())
                .numberOfQuotas(loan.getNumberOfQuotas())
                .firstPaymentDate(loan.getFirstPaymentDate())
                .deadline(loan.getDeadline())
                .description(loan.getDescription())
                .loanState(loan.getLoanState())
                .earnings(loan.getEarnings())
                .legalExpenses(loan.getLegalExpenses())
                .searchKey(loan.getSearchKey())
                .createdAt(loan.getCreatedAt())
                .updatedAt(loan.getUpdatedAt())
                .client(ClientMapper.clientsDto(loan.getClient()))
                .build();
    }


    public static LoanEntity loanALoanDto(Loan loan) {
        return LoanEntity.builder()
                .id(loan.getId())
                .amount(loan.getAmount())
                .paymentCycle(loan.getPaymentCycle())
                .deadline(loan.getDeadline())
                .description(loan.getDescription())
                .createdAt(loan.getCreatedAt())
                .updatedAt(loan.getUpdatedAt())
                .client(ClientMapper.clientAClientDto(loan.getClient()))
                .build();
    }

    public static Loan loanDtoALoansAPaymentSchedule(LoanEntity loan) {
        return Loan.builder()
                .id(loan.getId())
                .amount(loan.getAmount())
                .paymentCycle(loan.getPaymentCycle())
                .interest(loan.getInterest())
                .numberOfQuotas(loan.getNumberOfQuotas())
                .firstPaymentDate(loan.getFirstPaymentDate())
                .deadline(loan.getDeadline())
                .description(loan.getDescription())
                .simplePromissoryNote(loan.getSimplePromissoryNote())
                .notarialPromissoryNote(loan.getNotarialPromissoryNote())
                .specialPower(loan.getSpecialPower())
                .legalExpenses(loan.getLegalExpenses())
                .loanState(loan.getLoanState())
                .paymentSchedules(loan.getPaymentSchedule().stream().map(PaymentScheduleMapper::paymentScheduleDtoAPaymentSchedule).sorted(Comparator.comparingInt(PaymentSchedule::getQuotaNumber)).toList())
                .earnings(loan.getEarnings())
                .searchKey(loan.getSearchKey())
                .createdAt(loan.getCreatedAt())
                .updatedAt(loan.getUpdatedAt())
                .client(ClientMapper.clientsDto(loan.getClient()))
                .build();
    }

    public static List<LoanReportDto> loanReport(List<PaymentScheduleEntity> paymentScheduleEntities) {
        return paymentScheduleEntities.stream().map(ele -> LoanReportDto.builder()
                .amount(ele.getAmount())
                .paymentStatus(ele.getPaymentStatus().name())
                .quotaNumber(ele.getPaymentReference())
                .build()).toList();
    }


    public static LoanIndicatorDTO indicatorDTO(Object object) {
        if (object instanceof Object[] array) {
            BigDecimal totalInvestedCapital = (BigDecimal) array[0];
            BigDecimal investedCapital = (BigDecimal) array[1];
            BigDecimal earnings = (BigDecimal) array[2];
            Long totalActiveLoans = ((Number) array[3]).longValue();
            Long activeLoans = ((Number) array[4]).longValue();
            Long totalLoansPaid = ((Number) array[5]).longValue();
            Long loansPaid = ((Number) array[6]).longValue();
            return new LoanIndicatorDTO(totalInvestedCapital, investedCapital, earnings, totalActiveLoans, activeLoans, totalLoansPaid, loansPaid);
        }
        throw new BusinessException(BusinessException.Type.ERROR_BD);
    }


}
