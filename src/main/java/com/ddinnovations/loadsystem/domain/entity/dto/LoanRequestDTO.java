package com.ddinnovations.loadsystem.domain.entity.dto;

import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class LoanRequestDTO {
    private String id;
    private String fullName;
    private BigDecimal amount;
    private String state;
    private PaymentOfPayroll paymentCycle;
    private String createdAt;

}
