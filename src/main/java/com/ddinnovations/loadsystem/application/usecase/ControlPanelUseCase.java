package com.ddinnovations.loadsystem.application.usecase;

import com.ddinnovations.loadsystem.domain.entity.ControlPanel;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

public interface ControlPanelUseCase {
    ResponseGlobal<ControlPanel> generateControlPanelReport();

    ResponseGlobal<BigDecimal> generateLegalExpenses(String startDate, String endDate);
}
