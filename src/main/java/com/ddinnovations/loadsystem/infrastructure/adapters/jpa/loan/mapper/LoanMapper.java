package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.mapper;

import com.ddinnovations.loadsystem.domain.entity.Loan;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.mapper.ClientMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.LoanEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.paymentschedule.mapper.PaymentScheduleMapper;

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
                .firstPaymentDate(loan.getFirstPaymentDate())
                .deadline(loan.getDeadline())
                .description(loan.getDescription())
                .loanState(loan.getLoanState())
                .earnings(loan.getEarnings())
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
                .firstPaymentDate(loan.getFirstPaymentDate())
                .deadline(loan.getDeadline())
                .description(loan.getDescription())
                .loanState(loan.getLoanState())
                .paymentSchedules(loan.getPaymentSchedule().stream().map(PaymentScheduleMapper::paymentScheduleDtoAPaymentSchedule).toList())
                .earnings(loan.getEarnings())
                .searchKey(loan.getSearchKey())
                .createdAt(loan.getCreatedAt())
                .updatedAt(loan.getUpdatedAt())
                .client(ClientMapper.clientsDto(loan.getClient()))
                .build();
    }


}
