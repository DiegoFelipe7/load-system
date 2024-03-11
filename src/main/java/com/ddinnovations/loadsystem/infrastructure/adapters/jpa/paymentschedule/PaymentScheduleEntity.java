package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.paymentschedule;

import com.ddinnovations.loadsystem.domain.entity.Loan;
import com.ddinnovations.loadsystem.domain.entity.enums.LoanState;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.ClientsEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.LoanEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "payment-schedule")
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String paymentDate;
    private BigDecimal amount;
    private int quotaNumber;
    @ManyToOne(targetEntity = LoanEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id")
    private LoanEntity loan;
    private PaymentOfPayroll paymentCycle;
    private PaymentStatus paymentStatus;
    private String searchKey;

    @PrePersist()
    public void insert() {
        this.searchKey = (this.paymentDate + "|" + this.amount + "|" + this.quotaNumber + '|' + this.paymentStatus.name() + '|' + this.paymentCycle.name()).toLowerCase();
    }

}
