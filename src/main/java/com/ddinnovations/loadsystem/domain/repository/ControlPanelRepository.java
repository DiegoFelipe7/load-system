package com.ddinnovations.loadsystem.domain.repository;

import com.ddinnovations.loadsystem.domain.entity.ControlPanel;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;

import java.util.concurrent.CompletableFuture;

public interface ControlPanelRepository {
   ResponseGlobal<ControlPanel> generateControlPanelReport();
}
