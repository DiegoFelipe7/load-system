package com.ddinnovations.loadsystem.infrastructure.points.controller;

import com.ddinnovations.loadsystem.application.service.LoanApplicationService;
import com.ddinnovations.loadsystem.domain.entity.LoanApplication;
import com.ddinnovations.loadsystem.domain.entity.dto.Id;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanRequestDTO;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsLoan;
import com.ddinnovations.loadsystem.domain.entity.response.Params;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/loan-application")
@RequiredArgsConstructor
public class LoanApplicationController {
    private final LoanApplicationService loanApplicationService;

    @PostMapping()
    public void createLoanApplication(@RequestBody LoanApplication loanApplication) {
        loanApplicationService.createLoanApplication(loanApplication);
    }

    @GetMapping(path = "/{id}")
    public ResponseGlobal<LoanApplication> findByIdLoanApplication(@PathVariable("id") String id) {
        return loanApplicationService.findByIdLoanApplication(id);
    }


    @GetMapping()
    public ResponseGlobalPagination<List<LoanRequestDTO>> findAllLoanApplications(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "limit", defaultValue = "10", required = false) int limit,
            @RequestParam(value = "filterCriteriaText", defaultValue = "", required = false) String filterCriteriaText,
            @RequestParam(value = "sort", defaultValue = "createAt", required = false) String sort) {

        return loanApplicationService.findAllLoanApplication(new ParamsLoan(page, limit, Sort.by(sort), filterCriteriaText, PaymentOfPayroll.Mensual,""));
    }

    @PatchMapping(path = "/approve/{id}")
    public ResponseGlobal<LoanApplication> approveLoanApplication(@PathVariable("id") String id) {
        return loanApplicationService.approveLoanApplication(id);
    }


    @DeleteMapping(path = "/reject/{id}")
    public ResponseGlobal<Id> rejectLoanApplication(@PathVariable("id") String id) {
        return loanApplicationService.rejectLoanApplication(id);
    }

}
