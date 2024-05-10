package com.ddinnovations.loadsystem.infrastructure.points.controller;

import com.ddinnovations.loadsystem.application.service.ControlPanelService;
import com.ddinnovations.loadsystem.domain.entity.ControlPanel;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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

    @GetMapping(path = "/legal-expenses")
    public ResponseGlobal<BigDecimal> generateLegalExpenses(@RequestParam(value = "startDate") String startDate,
                                                            @RequestParam(value = "endDate") String endDate) {
        return controlPanelService.generateLegalExpenses(startDate, endDate);
    }

}
