package com.ddinnovations.loadsystem.domain.repository;

import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentDTO;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentIndicatorsDto;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;

public interface PaymentScheduleRepository {
    ResponseGlobal<PaymentSchedule> makePayment(String id , PaymentDTO paymentDTO);
    ResponseGlobal<PaymentIndicatorsDto> paymentIndicators();

    void updateStatusToLate();
}
