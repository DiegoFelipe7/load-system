package com.ddinnovations.loadsystem.infrastructure.points.controller;

import com.ddinnovations.loadsystem.application.service.PaymentScheduleService;
import com.ddinnovations.loadsystem.domain.entity.Loan;
import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentIndicatorsDto;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsLoan;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsPaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/payment-schedule")
@RequiredArgsConstructor
public class PaymentScheduleController {
    private final PaymentScheduleService paymentScheduleService;

    @GetMapping(path = "/{id}")
    public ResponseGlobal<PaymentSchedule> findByIdPaymentSchedule(@PathVariable("id") String id) {
        return paymentScheduleService.findByIdPaymentSchedule(id);
    }

    @GetMapping(path = "/indicators")
    public ResponseGlobal<PaymentIndicatorsDto> paymentIndicators() {
        return paymentScheduleService.paymentIndicators();
    }

    @GetMapping()
    public ResponseGlobalPagination<List<PaymentSchedule>> findAllLoan(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "limit", defaultValue = "10", required = false) int limit,
            @RequestParam(value = "filterCriteriaText", defaultValue = "", required = false) String filterCriteriaText,
            @RequestParam(value = "sort", defaultValue = "createdAt", required = false) String sort,
            @RequestParam(value = "paymentCycle", required = false) PaymentOfPayroll paymentCycle,
            @RequestParam(value = "startDate") String startDate,
            @RequestParam(value = "paymentStatus", required = false) PaymentStatus paymentStatus) {
        return paymentScheduleService.findAllPaymentSchedule(new ParamsPaymentSchedule(page, limit, Sort.by(sort), filterCriteriaText, paymentCycle, paymentStatus, startDate));
    }

    @PatchMapping(path = "make-payment/{id}")
    public ResponseGlobal<PaymentSchedule> createLoan(@PathVariable("id") String id) {
        return paymentScheduleService.makePayment(id);
    }

}
