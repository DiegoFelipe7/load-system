package com.ddinnovations.loadsystem.domain.repository;

import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsPaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;

import java.util.List;

public interface PaymentScheduleRepository {
    ResponseGlobalPagination<List<PaymentSchedule>> findAllPaymentSchedule(ParamsPaymentSchedule paymentSchedule);
    ResponseGlobal<PaymentSchedule> findByIdPaymentSchedule(String id);
    ResponseGlobal<PaymentSchedule> makePayment(String id);
}
