package com.ddinnovations.loadsystem.domain.entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Params {
    private int page;
    private int limit;
    private Sort sort;
    private String filterCriteriaText;

    public Params(int page, int limit, Sort sort) {
        this.page = page;
        this.limit = limit;
        this.sort = sort;
    }
}
