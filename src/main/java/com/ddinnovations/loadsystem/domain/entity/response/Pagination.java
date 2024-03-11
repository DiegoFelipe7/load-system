package com.ddinnovations.loadsystem.domain.entity.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Pagination   {
    private int page;
    private int size;
    private int totalPages;
    private int totalElements;


    public Pagination(int page, int size, int totalElements) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) this.totalElements / this.size);

    }
}
