package com.ddinnovations.loadsystem.domain.entity;

import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class PaymentSchedule {
    private String id;
    private String paymentDate;
    private BigDecimal amount;
    private int quotaNumber;
    private Loan loan;
    private PaymentOfPayroll paymentCycle;
    private PaymentStatus paymentStatus;
    private String searchKey;

    public PaymentSchedule(String paymentDate, BigDecimal amount, int quotaNumber, PaymentOfPayroll paymentCycle, PaymentStatus paymentStatus) {
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.quotaNumber = quotaNumber;
        this.paymentCycle = paymentCycle;
        this.paymentStatus = paymentStatus;
    }
}
