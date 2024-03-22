package com.ddinnovations.loadsystem.domain.repository;

import com.ddinnovations.loadsystem.domain.entity.LoanApplication;
import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.dto.Id;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanRequestDTO;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsLoan;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsLoanRequest;
import com.ddinnovations.loadsystem.domain.entity.response.Params;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;

import java.util.List;

public interface LoanApplicationRepository {
    void createLoanApplication(LoanApplication loanApplication);

    ResponseGlobalPagination<List<LoanRequestDTO>> findAllLoanApplication(ParamsLoanRequest params);

    ResponseGlobal<LoanApplication> findByIdLoanApplication(String id);

    ResponseGlobal<Id> approveLoanApplication(String id);

    ResponseGlobal<Id> rejectLoanApplication(String id);
}
