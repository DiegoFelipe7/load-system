package com.ddinnovations.loadsystem.domain.entity.params;

import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.domain.entity.response.Params;
import lombok.*;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParamsLoanRequest extends Params {
    private String startDate;
    private PaymentOfPayroll paymentCycle;

    public ParamsLoanRequest(int page, int limit, Sort sort, String filterCriteriaText, String startDate, PaymentOfPayroll paymentCycle) {
        super(page, limit, sort, filterCriteriaText);
        this.startDate = startDate;
        this.paymentCycle = paymentCycle;
    }
}
