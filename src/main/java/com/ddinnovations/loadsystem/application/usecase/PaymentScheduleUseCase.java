package com.ddinnovations.loadsystem.application.usecase;

import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentDTO;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentIndicatorsDto;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;

public interface PaymentScheduleUseCase {

    ResponseGlobal<PaymentSchedule> makePayment(String id, PaymentDTO paymentDTO);

    ResponseGlobal<PaymentIndicatorsDto> paymentIndicators();
}
