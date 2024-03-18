package com.ddinnovations.loadsystem.domain.entity;

import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime updatedAt;

}
