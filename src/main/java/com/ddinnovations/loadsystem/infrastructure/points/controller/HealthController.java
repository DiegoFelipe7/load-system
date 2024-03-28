package com.ddinnovations.loadsystem.infrastructure.points.controller;

import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/" )
public class HealthController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseGlobal<String> health(){
        return new ResponseGlobal<>("CashMoneyRd");
    }
}
