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
    private String identification;
    private OrderBy orderBy;

    public ParamsClients(int page, int limit, Sort sort, String filterCriteriaText, String identification, OrderBy orderBy) {
        super(page, limit, sort, filterCriteriaText);
        this.identification = identification;
        this.orderBy=orderBy;
    }
}
