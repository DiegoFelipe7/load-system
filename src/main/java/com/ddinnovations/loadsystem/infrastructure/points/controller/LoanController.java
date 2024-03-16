package com.ddinnovations.loadsystem.infrastructure.points.controller;

import com.ddinnovations.loadsystem.application.service.LoanService;
import com.ddinnovations.loadsystem.domain.entity.Loan;
import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanIndicatorDTO;
import com.ddinnovations.loadsystem.domain.entity.enums.LoanState;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsLoan;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/loan")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @PostMapping()
    public ResponseGlobal<Loan> createLoan(@RequestBody Loan loan) {
        return loanService.createLoan(loan);
    }


    @PostMapping(path = "/generate-payment-schedule")
    public ResponseGlobal<List<PaymentSchedule>> generatePaymentSchedule(@RequestBody Loan loan) {
        return loanService.generatePaymentSchedule(loan);
    }

    @GetMapping()
    public ResponseGlobalPagination<List<Loan>> findAllLoan(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "limit", defaultValue = "10", required = false) int limit,
            @RequestParam(value = "filterCriteriaText", defaultValue = "", required = false) String filterCriteriaText,
            @RequestParam(value = "startDate", defaultValue = "", required = false) String startDate,
            @RequestParam(value = "sort", defaultValue = "createdAt", required = false) String sort,
            @RequestParam(value = "paymentCycle", required = false) PaymentOfPayroll paymentCycle,
            @RequestParam(value = "loanState", required = false) LoanState loanState) {

        return loanService.findAllLoan(new ParamsLoan(page, limit, Sort.by(sort), filterCriteriaText, paymentCycle, loanState, startDate));
    }

    @GetMapping(path = "/indicators")
    public ResponseGlobal<LoanIndicatorDTO> loanIndicators() {
        return loanService.loanIndicators();
    }

    @GetMapping(path = "/report/{id}")
    public ResponseEntity<byte[]> loanReport(@PathVariable("id") String id) {
        return ResponseEntity.ok(loanService.loanReport(id));
    }

    @GetMapping(path = "/{id}")
    public ResponseGlobal<Loan> findByIdLoan(@PathVariable("id") String id) {
        return loanService.findByIdLoan(id);
    }

    @PatchMapping(path = "/approve/{id}")
    public ResponseGlobal<Loan> approveLoan(@PathVariable("id") String id, @RequestBody Loan loan) {
        return loanService.approveLoan(id, loan);
    }


    @DeleteMapping(path = "/cancel/{id}")
    public ResponseGlobal<Loan> cancelLoan(@PathVariable("id") String id) {
        return loanService.cancelLoan(id);
    }
}
