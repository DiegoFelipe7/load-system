package com.ddinnovations.loadsystem.domain.repository;

import com.ddinnovations.loadsystem.domain.entity.TypeOfLoan;
import com.ddinnovations.loadsystem.domain.entity.response.Params;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;

import java.util.List;

public interface TypeOfLoanRepository {
    ResponseGlobalPagination<List<TypeOfLoan>> findAllTypeOfLoan(Params params);
    ResponseGlobal<TypeOfLoan> findByIdTypeOfLoan(String id);
    ResponseGlobal<TypeOfLoan> createTypeOfLoan(TypeOfLoan typeOfLoan);
    ResponseGlobal<TypeOfLoan> update(String id, TypeOfLoan typeOfLoan);

    ResponseGlobal<TypeOfLoan> updateState(String id);
}
