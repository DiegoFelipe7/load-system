package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule;

import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.LoanEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "payment_schedule")
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String paymentDate;
    private String paymentReference;
    private BigDecimal amount;
    private BigDecimal earnings;
    private int quotaNumber;
    @ManyToOne(targetEntity = LoanEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_id")
    private LoanEntity loan;
    private PaymentOfPayroll paymentCycle;
    private PaymentStatus paymentStatus;
    private String searchKey;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @PrePersist()
    public void insert() {
        this.createdAt = LocalDateTime.now();
        this.paymentReference = this.loan.getId().split("-")[0].concat("-N" + this.quotaNumber);
        this.updatedAt = LocalDateTime.now();
        this.searchKey = (this.paymentReference + "|" + this.loan.getClient().getIdentification() + "|" + this.paymentDate + "|" + this.amount + "|" + this.quotaNumber + '|' + this.paymentStatus.name() + '|' + this.paymentCycle.name()).toLowerCase();
    }

    @PreUpdate()
    public void update() {
        this.updatedAt = LocalDateTime.now();
        this.searchKey = (this.paymentDate + "|" + this.amount + "|" + this.quotaNumber + '|' + this.paymentStatus.name() + '|' + this.paymentCycle.name()).toLowerCase();
    }

}
