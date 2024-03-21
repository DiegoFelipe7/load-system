package com.ddinnovations.loadsystem.domain.entity.dto;

import java.math.BigDecimal;

public record PaymentIndicatorsDto( BigDecimal totalBalance,BigDecimal raisedMoney, Long paymentsMade, Long overduePayments) {
}
