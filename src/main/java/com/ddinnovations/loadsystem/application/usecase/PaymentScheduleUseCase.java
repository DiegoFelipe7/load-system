package com.ddinnovations.loadsystem.application.usecase;

import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.dto.PaymentIndicatorsDto;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;

import java.util.List;

public interface PaymentScheduleUseCase {

    ResponseGlobal<PaymentSchedule> makePayment(String id);

    ResponseGlobal<PaymentIndicatorsDto> paymentIndicators();
}
