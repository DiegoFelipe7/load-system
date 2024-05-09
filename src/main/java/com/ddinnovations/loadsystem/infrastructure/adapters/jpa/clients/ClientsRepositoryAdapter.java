package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients;


import com.ddinnovations.loadsystem.domain.entity.Clients;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.dto.CustomerIndicatorDto;
import com.ddinnovations.loadsystem.domain.entity.enums.ActionType;
import com.ddinnovations.loadsystem.domain.entity.enums.ClientFileType;
import com.ddinnovations.loadsystem.domain.entity.enums.FileType;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsClients;
import com.ddinnovations.loadsystem.domain.entity.response.Pagination;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import com.ddinnovations.loadsystem.domain.repository.ClientsRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.back.account.mapper.BackAccountMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.mapper.ClientMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.filters.ClientSpecification;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.AdapterOperations;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.GenerateDates;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.LoanEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.workin.information.mapper.WorkingInformationMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.s3.service.S3Service;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public class ClientsRepositoryAdapter extends AdapterOperations<Clients, ClientsEntity, String, ClientsDtoRepository> implements ClientsRepository {
    private final S3Service s3Service;

    protected ClientsRepositoryAdapter(ClientsDtoRepository repository, ObjectMapper mapper, S3Service s3Service) {
        super(repository, mapper, d -> mapper.map(d, Clients.ClientsBuilder.class).build());
        this.s3Service = s3Service;

    }

    @Override
    public ResponseGlobalPagination<List<Clients>> findAllClients(ParamsClients params) {
        ClientSpecification specification = new ClientSpecification(params.getFilterCriteriaText(), params.getOrderBy(), params.getStartDate(), params.getEndDate());
        PageRequest pages = PageRequest.of(params.getPage(), params.getLimit());
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

    @Override
    @Transactional
    public ResponseGlobal<Clients> update(String id, Clients client) {
        ClientsEntity clientEntity = this.getByIdClient(id);
        clientEntity.setId(client.getId());
        clientEntity.setEmail(client.getEmail());
        clientEntity.setFullName(client.getFullName());
        clientEntity.setTypeOfIdentification(client.getTypeOfIdentification());
        clientEntity.setIdentification(client.getIdentification());
        clientEntity.setPhone(client.getPhone());
        clientEntity.setCivilStatus(client.getCivilStatus());
        clientEntity.setProfession(client.getProfession());
        clientEntity.setAddress(client.getAddress());
        clientEntity.setCity(client.getCity());
        clientEntity.setWorkingInformation(WorkingInformationMapper.workingInformationAWorkingInformationDto(client.getWorkingInformation()));
        clientEntity.setBackAccount(BackAccountMapper.bankAccountABacKAccountDto(client.getBankAccount()));
        return new ResponseGlobal<>(ClientMapper.clientDtoAClient(repository.save(clientEntity)));
    }

    @Override
    public ResponseGlobal<Boolean> searchById(String identification) {
        return new ResponseGlobal<>(this.repository.existsByIdentification(identification));
    }

    @Override
    public ResponseGlobal<CustomerIndicatorDto> customerIndicators() {
        Object object = repository.getIndicators(GenerateDates.starDateFilter(), GenerateDates.endDateFilter());
        return new ResponseGlobal<>(ClientMapper.customerIndicator(object));
    }

    @Override
    public ResponseGlobal<String> documentUpload(String id, ClientFileType fileType, MultipartFile file) {
        ClientsEntity clientsEntity = this.getByIdClient(id);
        String fileName = s3Service.upload(id, file, ActionType.client);
        switch (fileType) {
            case identificationCard -> clientsEntity.setIdentificationCard(fileName);
            case jobLetter -> clientsEntity.setJobLetter(fileName);
            case payrollStatements -> clientsEntity.setPayrollStatements(fileName);
        }
        repository.save(clientsEntity);
        return new ResponseGlobal<>(fileName);
    }

    @Override
    public ResponseGlobal<String> updateDocumentUpload(String id, ClientFileType fileType, MultipartFile file) {
        ClientsEntity clientsEntity = getByIdClient(id);
        String oldFileName = switch (fileType) {
            case identificationCard -> clientsEntity.getIdentificationCard();
            case jobLetter -> clientsEntity.getJobLetter();
            case payrollStatements -> clientsEntity.getPayrollStatements();
        };
        if (oldFileName == null || oldFileName.isEmpty()) {
            throw new BusinessException(BusinessException.Type.ERROR_UPLOAD_FILE);
        }
        s3Service.deleteFile(oldFileName);
        return this.documentUpload(id, fileType, file);
    }

    private ClientsEntity getByIdClient(String id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.USER_NOT_EXIST));
    }
}
