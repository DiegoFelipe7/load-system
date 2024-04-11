package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan;

import com.ddinnovations.loadsystem.domain.entity.enums.LoanState;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
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
@ToString
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private BigDecimal amount;
    private PaymentOfPayroll paymentCycle;
    private double interest;
    private int deadline;
    private int numberOfQuotas;
    private int numberOfPayments;
    private String description;
    private String searchKey;
    private LoanState loanState;
    private BigDecimal earnings;
    private String firstPaymentDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne(targetEntity = ClientsEntity.class, fetch = FetchType.EAGER)
    private ClientsEntity client;
    @OneToMany(targetEntity = PaymentScheduleEntity.class, fetch = FetchType.EAGER, mappedBy = "loan", cascade = CascadeType.ALL)
    private List<PaymentScheduleEntity> paymentSchedule;


    @PrePersist()
    public void insert() {
        this.interest = 0;
        this.numberOfPayments = 0;
        this.earnings = BigDecimal.ZERO;
        this.loanState = LoanState.Pendiente;
        this.searchKey = (this.client.getFullName() + "|" + this.client.getIdentification() + "|" + this.amount.toString() + '|' + this.paymentCycle.name() + '|' + this.deadline).toLowerCase();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate()
    public void update() {
        this.searchKey = (this.client.getFullName() + "|" + this.client.getIdentification() + "|" + this.amount.toString() + '|' + this.paymentCycle.name() + '|' + this.deadline).toLowerCase();
        this.updatedAt = LocalDateTime.now();
    }

    public BigDecimal earnings() {
        double interestRate = interest / 100.0;
        return this.amount.multiply(BigDecimal.valueOf(interestRate))
                .multiply(BigDecimal.valueOf(this.numberOfQuotas));
    }


    public BigDecimal valuePaid() {
        return this.paymentSchedule.stream()
                .filter(ele -> ele.getPaymentStatus().equals(PaymentStatus.Pagado))
                .map(PaymentScheduleEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public PaymentScheduleEntity paymentSchedule(String id) {
        return this.getPaymentSchedule().stream()
                .filter(ele -> ele.getId().equals(id))
                .findFirst()
                .orElseGet(() -> PaymentScheduleEntity.builder()
                        .paymentReference("0")
                        .amount(BigDecimal.ZERO)
                        .outstandingBalance(BigDecimal.ZERO)
                        .quotaNumber(0)
                        .build());
    }

    public BigDecimal balance() {
        return amount.subtract(valuePaid()).max(BigDecimal.ZERO);
    }


}
