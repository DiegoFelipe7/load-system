package com.ddinnovations.loadsystem.application.usecase;

import com.ddinnovations.loadsystem.domain.entity.ControlPanel;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;

import java.util.concurrent.CompletableFuture;

public interface ControlPanelUseCase {
   ResponseGlobal<ControlPanel>  generateControlPanelReport();
}
