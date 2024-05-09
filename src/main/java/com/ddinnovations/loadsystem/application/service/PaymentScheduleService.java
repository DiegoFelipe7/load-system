package com.ddinnovations.loadsystem.application.service;

import com.ddinnovations.loadsystem.application.usecase.PaymentScheduleUseCase;
import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.User;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentDTO;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentIndicatorsDto;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.repository.PaymentScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentScheduleService implements PaymentScheduleUseCase {

    private final PaymentScheduleRepository paymentScheduleRepository;


    @Override
    public ResponseGlobal<PaymentSchedule> makePayment(String id, PaymentDTO paymentDTO, User user) {
        return paymentScheduleRepository.makePayment(id, paymentDTO, user);
    }

    @Override
    public ResponseGlobal<PaymentIndicatorsDto> paymentIndicators() {
        return paymentScheduleRepository.paymentIndicators();
    }
}
