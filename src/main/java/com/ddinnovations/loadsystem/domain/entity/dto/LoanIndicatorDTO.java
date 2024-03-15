package com.ddinnovations.loadsystem.domain.entity.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class LoanIndicatorDTO {
    private BigDecimal totalInvestedCapital;
    private BigDecimal investedCapital;
    private Long totalActiveLoans;
    private Long activeLoans;
    private Long totalLoansPaid;
    private Long loansPaid;
}
