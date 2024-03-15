package com.ddinnovations.loadsystem.domain.entity.params;

import com.ddinnovations.loadsystem.domain.entity.enums.OrderBy;
import com.ddinnovations.loadsystem.domain.entity.response.Params;
import lombok.*;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class ParamsClients extends Params {
    private OrderBy orderBy;
    private String startDate;
    private String endDate;

    public ParamsClients(int page, int limit, Sort sort, String filterCriteriaText, OrderBy orderBy, String startDate, String endDate) {
        super(page, limit, sort, filterCriteriaText);
        this.orderBy = orderBy;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
