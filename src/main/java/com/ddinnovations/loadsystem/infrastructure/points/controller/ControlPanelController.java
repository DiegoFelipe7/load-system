package com.ddinnovations.loadsystem.infrastructure.points.controller;

import com.ddinnovations.loadsystem.application.service.ControlPanelService;
import com.ddinnovations.loadsystem.domain.entity.ControlPanel;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "api/control-panel")
@RequiredArgsConstructor
public class ControlPanelController {
    private final ControlPanelService controlPanelService;

    @GetMapping(path = "/indicators")
    public ResponseGlobal<ControlPanel> generateControlPanelReport() {
        return controlPanelService.generateControlPanelReport();
    }

}
