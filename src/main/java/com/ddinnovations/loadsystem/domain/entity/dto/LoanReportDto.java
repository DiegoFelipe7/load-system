package com.ddinnovations.loadsystem.domain.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoanReportDto {
    private String amount;
    private String quotaNumber;
    private String paymentStatus;
}
