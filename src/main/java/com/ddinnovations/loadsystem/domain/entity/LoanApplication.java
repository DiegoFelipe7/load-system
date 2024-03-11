package com.ddinnovations.loadsystem.domain.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class LoanApplication {
    private String id;
    private Clients client;
    private WorkingInformation workingInformation;
    private Loan loan;
    private BankAccount bankAccount;
    private PersonalReference personalReference;
    private LocalDateTime createdAt;
}
