package com.ddinnovations.loadsystem.application.service;

import com.ddinnovations.loadsystem.application.usecase.LoanApplicationUseCase;
import com.ddinnovations.loadsystem.domain.entity.LoanApplication;
import com.ddinnovations.loadsystem.domain.entity.dto.Id;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanRequestDTO;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsLoanRequest;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import com.ddinnovations.loadsystem.domain.repository.LoanApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanApplicationService  implements LoanApplicationUseCase {
    private final LoanApplicationRepository loanApplicationRepository;
    @Override
    public void createLoanApplication(LoanApplication loanApplication) {
        loanApplicationRepository.createLoanApplication(loanApplication);
    }
    @Override
    public ResponseGlobalPagination<List<LoanRequestDTO>> findAllLoanApplication(ParamsLoanRequest params) {
        return loanApplicationRepository.findAllLoanApplication(params);
    }



    @Override
    public ResponseGlobal<LoanApplication> findByIdLoanApplication(String id) {
        return loanApplicationRepository.findByIdLoanApplication(id);
    }

    @Override
    public ResponseGlobal<Id> approveLoanApplication(String id) {
        return loanApplicationRepository.approveLoanApplication(id);
    }

    @Override
    public ResponseGlobal<Id> rejectLoanApplication(String id) {
        return loanApplicationRepository.rejectLoanApplication(id);
    }
}
