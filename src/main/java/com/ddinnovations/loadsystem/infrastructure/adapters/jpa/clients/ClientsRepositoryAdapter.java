package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients;


import com.ddinnovations.loadsystem.domain.entity.Clients;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsClients;
import com.ddinnovations.loadsystem.domain.entity.response.Pagination;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import com.ddinnovations.loadsystem.domain.repository.ClientsRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.mapper.ClientMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.filters.ClientSpecification;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientsRepositoryAdapter extends AdapterOperations<Clients, ClientsEntity, String, ClientsDtoRepository> implements ClientsRepository {

    protected ClientsRepositoryAdapter(ClientsDtoRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Clients.ClientsBuilder.class).build());

    }

    @Override
    public ResponseGlobalPagination<List<Clients>> findAllClients(ParamsClients params) {
        ClientSpecification specification = new ClientSpecification(params.getFilterCriteriaText(), params.getIdentification(),params.getOrderBy());
        PageRequest pages = PageRequest.of(params.getPage(), params.getLimit(), params.getSort());
        List<Clients> clientList = repository.findAll(specification, pages)
                .stream()
                .map(ClientMapper::clientsDto)
                .toList();
        return new ResponseGlobalPagination<>(clientList, new Pagination(params.getPage(), params.getLimit(), ((int) repository.count())));

    }

    @Override
    public ResponseGlobal<Clients> findByIdClient(String id) {
        ClientsEntity clientEntity = this.getByIdClient(id);
        return new ResponseGlobal<>(ClientMapper.clientDtoAClient(clientEntity));
    }

    @Override
    public ResponseGlobal<Clients> createClient(Clients client) {
        if (this.repository.existsByIdentification(client.getIdentification())) {
            throw new BusinessException(BusinessException.Type.USER_IDENTIFICATION);
        }
        ClientsEntity clientEntity = this.repository.save(ClientMapper.clientAClientDto(client));
        return new ResponseGlobal<>(ClientMapper.clientDtoAClient(clientEntity));
    }

    //TODO: ACTUALIZAR EL CLIENTE
    @Override
    public ResponseGlobal<Clients> update(String id, Clients client) {
        ClientsEntity clientEntity = this.getByIdClient(id);
        clientEntity.setAddress(client.getAddress());
        clientEntity.setPhone(client.getPhone());
        clientEntity.setEmail(client.getEmail());
        clientEntity.setTypeOfIdentification(client.getTypeOfIdentification());
        clientEntity.setIdentification(client.getIdentification());
        return new ResponseGlobal<>(ClientMapper.clientDtoAClient(repository.save(clientEntity)));
    }

    @Override
    public ResponseGlobal<Boolean> searchById(String identification) {
        return new ResponseGlobal<>(this.repository.existsByIdentification(identification));
    }

    private ClientsEntity getByIdClient(String id) {
        System.out.println(id);
        return this.repository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.USER_NOT_EXIST));
    }
}
