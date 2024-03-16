package com.ddinnovations.loadsystem.domain.entity.dto;

import com.ddinnovations.loadsystem.domain.entity.Clients;
import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class LoanReportDTO {
    private Clients clients;
    private BigDecimal balance;
    private BigDecimal totalCredit;
    private BigDecimal valuePaid;
    private PaymentSchedule paymentSchedule;
    private List<PaymentSchedule> paymentSchedules;

}
