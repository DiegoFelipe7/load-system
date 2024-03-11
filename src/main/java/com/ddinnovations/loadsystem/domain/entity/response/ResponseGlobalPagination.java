package com.ddinnovations.loadsystem.domain.entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseGlobalPagination <T>{
    private T body;
    private Pagination pagination;

}
