package com.ddinnovations.loadsystem.application.service;

import com.ddinnovations.loadsystem.application.usecase.ClientsUseCase;
import com.ddinnovations.loadsystem.domain.entity.Clients;
import com.ddinnovations.loadsystem.domain.entity.dto.CustomerIndicatorDto;
import com.ddinnovations.loadsystem.domain.entity.enums.ClientFileType;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsClients;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import com.ddinnovations.loadsystem.domain.repository.ClientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientsService implements ClientsUseCase {
    private final ClientsRepository clientsRepository;

    @Override
    public ResponseGlobalPagination<List<Clients>> findAllClients(ParamsClients params) {
        return clientsRepository.findAllClients(params);
    }

    @Override
    public ResponseGlobal<Clients> findByIdClient(String id) {
        return clientsRepository.findByIdClient(id);
    }

    @Override
    public ResponseGlobal<Clients> createClient(Clients client) {
        return clientsRepository.createClient(client);
    }

    @Override
    public ResponseGlobal<Clients> update(String id, Clients client) {
        return clientsRepository.update(id, client);
    }

    @Override
    public ResponseGlobal<Boolean> searchById(String identification) {
        return clientsRepository.searchById(identification);
    }

    @Override
    public ResponseGlobal<CustomerIndicatorDto> customerIndicators() {
        return clientsRepository.customerIndicators();
    }

    @Override
    public ResponseGlobal<String> documentUpload(String id, ClientFileType fileType, MultipartFile file) {
        return clientsRepository.documentUpload(id, fileType, file);
    }

    @Override
    public ResponseGlobal<String> updateDocumentUpload(String id, ClientFileType fileType, MultipartFile file) {
        return this.clientsRepository.updateDocumentUpload(id, fileType, file);
    }
}
