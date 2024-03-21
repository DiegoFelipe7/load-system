package com.ddinnovations.loadsystem.application.service;

import com.ddinnovations.loadsystem.application.usecase.PaymentScheduleUseCase;
import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentIndicatorsDto;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsPaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import com.ddinnovations.loadsystem.domain.repository.PaymentScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentScheduleService implements PaymentScheduleUseCase {

    private final PaymentScheduleRepository paymentScheduleRepository;

    @Override
    public ResponseGlobalPagination<List<PaymentSchedule>> findAllPaymentSchedule(ParamsPaymentSchedule paymentSchedule) {
        return paymentScheduleRepository.findAllPaymentSchedule(paymentSchedule);
    }

    @Override
    public ResponseGlobal<PaymentSchedule> findByIdPaymentSchedule(String id) {
        return paymentScheduleRepository.findByIdPaymentSchedule(id);
    }

    @Override
    public ResponseGlobal<PaymentSchedule> makePayment(String id) {
        return paymentScheduleRepository.makePayment(id);
    }

    @Override
    public ResponseGlobal<PaymentIndicatorsDto> paymentIndicators() {
        return paymentScheduleRepository.paymentIndicators();
    }
}
