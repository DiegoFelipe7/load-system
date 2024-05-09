package com.ddinnovations.loadsystem.application.service;

import com.ddinnovations.loadsystem.application.usecase.ControlPanelUseCase;
import com.ddinnovations.loadsystem.domain.entity.ControlPanel;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.repository.ControlPanelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ControlPanelService implements ControlPanelUseCase {
    private final ControlPanelRepository controlPanelRepository;

    @Override
    public ResponseGlobal<ControlPanel>  generateControlPanelReport() {
        return controlPanelRepository.generateControlPanelReport();
    }
}
