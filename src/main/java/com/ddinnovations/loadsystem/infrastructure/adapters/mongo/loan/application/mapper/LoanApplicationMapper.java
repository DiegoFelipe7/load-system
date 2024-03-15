package com.ddinnovations.loadsystem.infrastructure.adapters.mongo.loan.application.mapper;

import com.ddinnovations.loadsystem.domain.entity.LoanApplication;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanRequestDTO;
import com.ddinnovations.loadsystem.infrastructure.adapters.mongo.loan.application.LoanApplicationEntity;

import java.time.LocalDateTime;

public class LoanApplicationMapper {
    private LoanApplicationMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static LoanApplication loanApplicationLoanApplicationDto(LoanApplicationEntity loanApplication) {
        return LoanApplication
                .builder()
                .id(loanApplication.getId())
                .client(loanApplication.getClient())
                .workingInformation(loanApplication.getWorkingInformation())
                .loan(loanApplication.getLoan())
                .bankAccount(loanApplication.getBankAccount())
                .personalReference(loanApplication.getPersonalReference())
                .createdAt(loanApplication.getCreateAt())
                .build();
    }


    public static LoanApplicationEntity loanApplicationDtoLoanApplication(LoanApplication loanApplication) {
        return LoanApplicationEntity
                .builder()
                .id(loanApplication.getId())
                .client(loanApplication.getClient())
                .workingInformation(loanApplication.getWorkingInformation())
                .loan(loanApplication.getLoan())
                .bankAccount(loanApplication.getBankAccount())
                .personalReference(loanApplication.getPersonalReference())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static LoanRequestDTO loanRequest(LoanApplicationEntity loanApplication) {
        return LoanRequestDTO
                .builder()
                .id(loanApplication.getId())
                .fullName(loanApplication.getClient().getFullName())
                .amount(loanApplication.getLoan().getAmount())
                .state("Pendiente")
                .paymentCycle(loanApplication.getLoan().getPaymentCycle())
                .createdAt(loanApplication.getCreateAt().toString())
                .build();
    }
}
