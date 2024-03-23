package com.ddinnovations.loadsystem.domain.entity;

import com.ddinnovations.loadsystem.domain.entity.enums.LoanState;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.ClientsEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class Loan {
    private String id;
    private BigDecimal amount;
    private PaymentOfPayroll paymentCycle;
    private double interest;
    private int deadline;
    private int numberOfPayments;
    private int numberOfQuotas;
    private String description;
    private String searchKey;
    private LoanState loanState;
    private BigDecimal earnings;
    private String firstPaymentDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime updatedAt;
    private List<PaymentSchedule> paymentSchedules;
    private Clients client;
}
