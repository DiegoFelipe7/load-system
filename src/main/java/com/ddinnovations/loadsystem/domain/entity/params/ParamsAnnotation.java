package com.ddinnovations.loadsystem.domain.entity.params;

import com.ddinnovations.loadsystem.domain.entity.enums.OrderBy;
import com.ddinnovations.loadsystem.domain.entity.response.Params;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParamsAnnotation extends Params {
    private OrderBy orderBy;
    private String startDate;
    private String endDate;

    public ParamsAnnotation(int page, int limit, String filterCriteriaText, OrderBy orderBy, String startDate,String endDate) {
        super(page, limit, filterCriteriaText);
        this.orderBy = orderBy;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
