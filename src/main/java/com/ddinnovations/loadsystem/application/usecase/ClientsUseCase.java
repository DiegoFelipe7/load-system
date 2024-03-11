package com.ddinnovations.loadsystem.application.usecase;

import com.ddinnovations.loadsystem.domain.entity.Clients;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsClients;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;

import java.util.List;

public interface ClientsUseCase {
    ResponseGlobalPagination<List<Clients>> findAllClients(ParamsClients params);
    ResponseGlobal<Clients> findByIdClient(String id);
    ResponseGlobal<Clients> createClient(Clients client);
    ResponseGlobal<Clients> update(String id, Clients client);
     ResponseGlobal<Boolean> searchById(String identification);
}
