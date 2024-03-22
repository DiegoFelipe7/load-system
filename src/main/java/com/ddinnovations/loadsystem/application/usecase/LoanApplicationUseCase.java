package com.ddinnovations.loadsystem.application.usecase;

import com.ddinnovations.loadsystem.domain.entity.LoanApplication;
import com.ddinnovations.loadsystem.domain.entity.dto.Id;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanRequestDTO;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsLoanRequest;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;

import java.util.List;

public interface LoanApplicationUseCase {
    void createLoanApplication(LoanApplication loanApplication);
    ResponseGlobalPagination<List<LoanRequestDTO>> findAllLoanApplication(ParamsLoanRequest params);

    ResponseGlobal<LoanApplication> findByIdLoanApplication(String id);

    ResponseGlobal<Id> approveLoanApplication(String id);

    ResponseGlobal<Id> rejectLoanApplication(String id);
}
