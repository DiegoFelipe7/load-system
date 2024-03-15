package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan;

import com.ddinnovations.loadsystem.domain.entity.enums.LoanState;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.ClientsEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule.PaymentScheduleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "loan")
@Entity
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private BigDecimal amount;
    private PaymentOfPayroll paymentCycle;
    private double interest;
    private int deadline;
    private int numberOfPayments;
    private String description;
    private String searchKey;
    private LoanState loanState;
    private BigDecimal earnings;
    private String firstPaymentDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne(targetEntity = ClientsEntity.class, fetch = FetchType.LAZY)
    private ClientsEntity client;
    @OneToMany(targetEntity = PaymentScheduleEntity.class, fetch = FetchType.EAGER, mappedBy = "loan", cascade = CascadeType.ALL)
    private List<PaymentScheduleEntity> paymentSchedule;


    @PrePersist()
    public void insert() {
        this.interest = 0;
        this.numberOfPayments = 0;
        this.earnings = BigDecimal.ZERO;
        this.loanState = LoanState.Pendiente;
        this.searchKey = (this.client.getEmail() + "|" + this.client.getIdentification() + "|" + this.amount.toString() + '|' + this.paymentCycle.name() + '|' + this.createdAt + '|' + this.deadline).toLowerCase();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate()
    public void update() {
        this.searchKey = (this.client.getEmail() + "|" + this.client.getIdentification() + "|" + this.amount.toString() + '|' + this.paymentCycle.name() + '|' + this.description + '|' + this.createdAt + '|' + this.deadline).toLowerCase();
        this.updatedAt = LocalDateTime.now();
    }

    public BigDecimal earnings() {
        double interestRate = interest / 100.0;
        return this.amount.multiply(BigDecimal.valueOf(interestRate))
                .multiply(BigDecimal.valueOf(this.deadline));
    }


}
