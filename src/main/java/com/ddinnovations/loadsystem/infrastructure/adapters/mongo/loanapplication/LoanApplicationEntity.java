package com.ddinnovations.loadsystem.infrastructure.adapters.mongo.loanapplication;

import com.ddinnovations.loadsystem.domain.entity.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Document(collection = "loan-application")
public class LoanApplicationEntity {
    private String id;
    private Clients client;
    private WorkingInformation workingInformation;
    private Loan loan;
    private BankAccount bankAccount;
    private PersonalReference personalReference;
    private LocalDateTime createAt;
}
