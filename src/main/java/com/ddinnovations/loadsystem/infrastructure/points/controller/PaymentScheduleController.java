package com.ddinnovations.loadsystem.infrastructure.points.controller;

import com.ddinnovations.loadsystem.application.service.PaymentScheduleService;
import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentIndicatorsDto;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/payment-schedule")
@RequiredArgsConstructor
public class PaymentScheduleController {
    private final PaymentScheduleService paymentScheduleService;

    @GetMapping(path = "/indicators")
    public ResponseGlobal<PaymentIndicatorsDto> paymentIndicators() {
        return paymentScheduleService.paymentIndicators();
    }


    @PatchMapping(path = "make-payment/{id}")
    public ResponseGlobal<PaymentSchedule> createLoan(@PathVariable("id") String id) {
        return paymentScheduleService.makePayment(id);
    }

}
