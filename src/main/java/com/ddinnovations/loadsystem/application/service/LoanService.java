package com.ddinnovations.loadsystem.application.service;

import com.ddinnovations.loadsystem.application.usecase.LoanUseCase;
import com.ddinnovations.loadsystem.domain.entity.Loan;
import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanIndicatorDTO;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsLoan;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import com.ddinnovations.loadsystem.domain.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService implements LoanUseCase {
    private final LoanRepository loanRepository;

    @Override
    public ResponseGlobal<Loan> createLoan(Loan loan) {
        return loanRepository.createLoan(loan);
    }

    @Override
    public ResponseGlobal<Loan> approveLoan(String id, Loan loan) {
        return loanRepository.approveLoan(id, loan);
    }

    @Override
    public ResponseGlobalPagination<List<Loan>> findAllLoan(ParamsLoan params) {
        return loanRepository.findAllLoan(params);
    }

    @Override
    public ResponseGlobal<List<PaymentSchedule>> generatePaymentSchedule(Loan loan) {
        return loanRepository.generatePaymentSchedule(loan);
    }

    @Override
    public ResponseGlobal<Loan> findByIdLoan(String id) {
        return loanRepository.findByIdLoan(id);
    }

    @Override
    public ResponseGlobal<Loan> cancelLoan(String id) {
        return loanRepository.cancelLoan(id);
        
    }

    @Override
    public ResponseGlobal<LoanIndicatorDTO> loanIndicators() {
        return loanRepository.loanIndicators();
    }
}
