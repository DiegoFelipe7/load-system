package com.ddinnovations.loadsystem.infrastructure.adapters.mongo.loan.application;

import com.ddinnovations.loadsystem.domain.entity.LoanApplication;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.dto.Id;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanRequestDTO;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsLoan;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsLoanRequest;
import com.ddinnovations.loadsystem.domain.entity.response.Pagination;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import com.ddinnovations.loadsystem.domain.repository.LoanApplicationRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.ClientsDtoRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.ClientsEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.ClientsRepositoryAdapter;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.mapper.ClientMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.LoanRepositoryAdapter;
import com.ddinnovations.loadsystem.infrastructure.adapters.mongo.helpers.AdapterOperations;
import com.ddinnovations.loadsystem.infrastructure.adapters.mongo.loan.application.mapper.LoanApplicationMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class LoanApplicationRepositoryAdapter extends AdapterOperations<LoanApplication, LoanApplicationEntity, String, LoanApplicationDtoRepository> implements LoanApplicationRepository {
    private final ClientsDtoRepository clientsDtoRepository;
    private final LoanRepositoryAdapter loanRepositoryAdapter;

    protected LoanApplicationRepositoryAdapter(LoanApplicationDtoRepository repository, ObjectMapper mapper, ClientsDtoRepository clientsDtoRepository, ClientsRepositoryAdapter clientsRepositoryAdapter, LoanRepositoryAdapter loanRepositoryAdapter, MongoTemplate mongoTemplate) {
        super(repository, mapper, d -> mapper.map(d, LoanApplication.LoanApplicationBuilder.class).build());
        this.clientsDtoRepository = clientsDtoRepository;
        this.loanRepositoryAdapter = loanRepositoryAdapter;
    }

    @Override
    @Async
    public void createLoanApplication(LoanApplication loanApplication) {
        if (this.clientsDtoRepository.existsByIdentification(loanApplication.getClient().getIdentification())) {
            throw new BusinessException(BusinessException.Type.THERE_IS_A_LOAN_APPLICATION);
        }
        LoanApplicationEntity loanApplicationEntity = LoanApplicationMapper.loanApplicationDtoLoanApplication(loanApplication);
        loanApplicationEntity.insert();
        repository.save(loanApplicationEntity);
    }


    @Override
    public ResponseGlobalPagination<List<LoanRequestDTO>> findAllLoanApplication(ParamsLoanRequest params) {
        PageRequest pages = PageRequest.of(params.getPage(), params.getLimit(), params.getSort());
        List<LoanRequestDTO> loanApplications = repository.findAllBy(pages)
                .stream()
                .map(LoanApplicationMapper::loanRequest)
                .toList();
        return new ResponseGlobalPagination<>(loanApplications, new Pagination(params.getPage(), params.getLimit(), ((int) repository.count())));

    }

    @Override
    public ResponseGlobal<LoanApplication> findByIdLoanApplication(String id) {
        LoanApplicationEntity loanApplication = this.getByIdLoanApplication(id);
        return new ResponseGlobal<>(LoanApplicationMapper.loanApplicationLoanApplicationDto(loanApplication));
    }


    @Override
    @Transactional
    public ResponseGlobal<LoanApplication> approveLoanApplication(String id) {
        LoanApplicationEntity loanApplicationEntity = this.getByIdLoanApplication(id);

        loanApplicationEntity.getClient().setWorkingInformation(loanApplicationEntity.getWorkingInformation());
        loanApplicationEntity.getClient().setBankAccount(loanApplicationEntity.getBankAccount());
        loanApplicationEntity.getClient().setPersonalReference(loanApplicationEntity.getPersonalReference());
        ClientsEntity clientsEntity = clientsDtoRepository.save(ClientMapper.approveClient(loanApplicationEntity.getClient()));
        //Creacion del prestamo
        loanApplicationEntity.getLoan().setClient(ClientMapper.clientDtoAClient(clientsEntity));
        loanRepositoryAdapter.createLoan(loanApplicationEntity.getLoan());
        repository.deleteById(loanApplicationEntity.getId());
        return new ResponseGlobal<>(LoanApplicationMapper.loanApplicationLoanApplicationDto(loanApplicationEntity));
    }

    @Override
    public ResponseGlobal<Id> rejectLoanApplication(String id) {
        LoanApplicationEntity loanApplication = this.getByIdLoanApplication(id);
        repository.deleteById(loanApplication.getId());
        return new ResponseGlobal<>(new Id(id));
    }


    private LoanApplicationEntity getByIdLoanApplication(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.LOAN_APPLICATION_NOT_FOUND));
    }
}
