package com.ddinnovations.loadsystem.domain.entity.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class LoanReportDto {
    private BigDecimal amount;
    private String quotaNumber;
    private String paymentStatus;
}
