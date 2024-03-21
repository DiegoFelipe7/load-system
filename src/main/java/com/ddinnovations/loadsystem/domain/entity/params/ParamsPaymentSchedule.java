package com.ddinnovations.loadsystem.domain.entity.params;

import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
import com.ddinnovations.loadsystem.domain.entity.response.Params;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParamsPaymentSchedule extends Params {
    private PaymentOfPayroll paymentCycle;
    private PaymentStatus paymentStatus;
    private String startDate;

    public ParamsPaymentSchedule(int page, int limit, Sort sort, String filterCriteriaText, PaymentOfPayroll paymentCycle, PaymentStatus paymentStatus, String startDate) {
        super(page, limit, sort, filterCriteriaText);
        this.paymentCycle = paymentCycle;
        this.paymentStatus = paymentStatus;
        this.startDate = startDate;
    }
}
