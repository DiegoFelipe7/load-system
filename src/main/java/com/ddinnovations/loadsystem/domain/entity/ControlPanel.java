package com.ddinnovations.loadsystem.domain.entity;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ControlPanel {
    private Long totalClients;
    private Long totalRequest;
    private BigDecimal totalCapitalInvested;
    private Long totalActiveLoans;
    private BigDecimal totalProfits;
    private BigDecimal profitsCollected;
    private Long totalLoansPaid;
    private Long totalLoansInArrears;
}
