package com.ddinnovations.loadsystem.domain.entity.params;

import com.ddinnovations.loadsystem.domain.entity.enums.LoanState;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.domain.entity.response.Params;
import lombok.*;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class ParamsLoan extends Params {
    private PaymentOfPayroll paymentCycle;
    private LoanState loanState;
    private String startDate;

    public ParamsLoan(int page, int limit, Sort sort, String filterCriteriaText, PaymentOfPayroll paymentCycle, LoanState loanState, String startDate) {
        super(page, limit, sort, filterCriteriaText);
        this.paymentCycle = paymentCycle;
        this.loanState = loanState;
        this.startDate = startDate;
    }
}
