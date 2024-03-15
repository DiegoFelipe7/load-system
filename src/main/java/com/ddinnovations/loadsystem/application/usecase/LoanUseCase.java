package com.ddinnovations.loadsystem.application.usecase;

import com.ddinnovations.loadsystem.domain.entity.Loan;
import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanIndicatorDTO;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsLoan;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;

import java.util.List;

public interface LoanUseCase {

    ResponseGlobal<Loan> createLoan(Loan loan);

    ResponseGlobal<Loan> approveLoan(String id, Loan loan);

    ResponseGlobalPagination<List<Loan>> findAllLoan(ParamsLoan params);

    ResponseGlobal<List<PaymentSchedule>> generatePaymentSchedule(Loan loan);

    ResponseGlobal<Loan> findByIdLoan(String id);

    ResponseGlobal<Loan> cancelLoan(String id);

    ResponseGlobal<LoanIndicatorDTO> loanIndicators();
}
