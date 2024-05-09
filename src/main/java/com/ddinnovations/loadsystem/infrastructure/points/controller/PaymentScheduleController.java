package com.ddinnovations.loadsystem.infrastructure.points.controller;

import com.ddinnovations.loadsystem.application.service.PaymentScheduleService;
import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentDTO;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentIndicatorsDto;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.infrastructure.adapters.security.config.CurrenUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/payment-schedule")
@RequiredArgsConstructor
public class PaymentScheduleController {
    private final PaymentScheduleService paymentScheduleService;
    private final CurrenUsers currenUsers;

    @GetMapping(path = "/indicators")
    public ResponseGlobal<PaymentIndicatorsDto> paymentIndicators() {
        return paymentScheduleService.paymentIndicators();
    }


    @PatchMapping(path = "make-payment/{id}")
    public ResponseGlobal<PaymentSchedule> createLoan(@PathVariable("id") String id, @RequestBody PaymentDTO paymentDTO) {
        return paymentScheduleService.makePayment(id, paymentDTO, currenUsers.getCurrentUser());
    }

}
