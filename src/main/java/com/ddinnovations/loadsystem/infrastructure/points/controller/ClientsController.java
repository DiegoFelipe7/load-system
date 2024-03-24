package com.ddinnovations.loadsystem.infrastructure.points.controller;

import com.ddinnovations.loadsystem.application.service.ClientsService;
import com.ddinnovations.loadsystem.domain.entity.Clients;
import com.ddinnovations.loadsystem.domain.entity.dto.CustomerIndicatorDto;
import com.ddinnovations.loadsystem.domain.entity.enums.OrderBy;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsClients;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/clients" )
@RequiredArgsConstructor
public class ClientsController {
    private final ClientsService clientsService;

    @PostMapping()
    public ResponseGlobal<Clients> createClient(@RequestBody Clients clients) {
        return clientsService.createClient(clients);
    }

    @GetMapping(path = "/indicators")
    public ResponseGlobal<CustomerIndicatorDto> customerIndicators() {
        return clientsService.customerIndicators();
    }

    @GetMapping()
    public ResponseGlobalPagination<List<Clients>> findAllClients(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "limit", defaultValue = "10", required = false) int limit,
            @RequestParam(value = "filterCriteriaText", defaultValue = "", required = false) String filterCriteriaText,
            @RequestParam(value = "orderBy", defaultValue = "", required = false) OrderBy orderBy,
            @RequestParam(value = "startDate", defaultValue = "", required = false) String startDate,
            @RequestParam(value = "endDate", defaultValue = "", required = false) String endDate) {
        return clientsService.findAllClients(new ParamsClients(page, limit, filterCriteriaText, orderBy, startDate, endDate));
    }

    @GetMapping(path = "/{id}")
    public ResponseGlobal<Clients> findByIdClient(@PathVariable("id") String id) {
        return clientsService.findByIdClient(id);
    }

    @GetMapping(path = "/search-by-id")
    public ResponseGlobal<Boolean> searchById(@RequestParam(value = "identification", required = true) String identification) {
        return clientsService.searchById(identification);
    }

    @PutMapping(path = "/{id}")
    public ResponseGlobal<Clients> update(@PathVariable("id") String id, @RequestBody Clients clients) {
        return clientsService.update(id, clients);
    }
}
