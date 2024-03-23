package com.ddinnovations.loadsystem.infrastructure.adapters.mongo.loan.application;

import com.ddinnovations.loadsystem.domain.entity.*;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
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
    private String searchKey;
    private PaymentOfPayroll paymentCycle;
    private LocalDateTime createdAt;

    public void insert() {
        this.searchKey = (this.client.getFullName() + "|" + this.client.getIdentification() + "|" + this.loan.getAmount() + '|' + this.loan.getPaymentCycle().name() + '|' + this.client.getEmail()).toLowerCase();
        this.paymentCycle = loan.getPaymentCycle();
    }


}
