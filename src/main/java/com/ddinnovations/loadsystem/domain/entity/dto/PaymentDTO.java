package com.ddinnovations.loadsystem.domain.entity.dto;

import java.math.BigDecimal;

public record PaymentDTO(BigDecimal balance, Boolean isFullPayment) {
}
