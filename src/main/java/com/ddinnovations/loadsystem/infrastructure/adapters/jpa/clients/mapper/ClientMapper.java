package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.mapper;

import com.ddinnovations.loadsystem.domain.entity.Clients;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.back.account.BackAccountEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.back.account.mapper.BackAccountMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.ClientsEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.personal.reference.PersonalReferenceEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.personal.reference.mapper.ReferenceMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.workin.information.WorkingInformationEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.workin.information.mapper.WorkingInformationMapper;

public class ClientMapper {
    private ClientMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Clients clientDtoAClient(ClientsEntity clientEntity) {
        return Clients.builder()
                .id(clientEntity.getId())
                .email(clientEntity.getEmail())
                .fullName(clientEntity.getFullName())
                .typeOfIdentification(clientEntity.getTypeOfIdentification())
                .identification(clientEntity.getIdentification())
                .phone(clientEntity.getPhone())
                .civilStatus(clientEntity.getCivilStatus())
                .profession(clientEntity.getProfession())
                .address(clientEntity.getAddress())
                .houseNumber(clientEntity.getHouseNumber())
                .sector(clientEntity.getSector())
                .typeOfResidence(clientEntity.getTypeOfResidence())
                .state(clientEntity.isState())
                .createdAt(clientEntity.getCreatedAt())
                .updatedAt(clientEntity.getUpdatedAt())
                .workingInformation(WorkingInformationMapper.workingInformationDtoAWorkingInformation(clientEntity.getWorkingInformation()))
                .bankAccount(BackAccountMapper.bankAccountDtoABacKAccount(clientEntity.getBackAccount()))
                .build();
    }

    public static Clients clientsDto(ClientsEntity clientEntity) {
        return Clients.builder()
                .id(clientEntity.getId())
                .email(clientEntity.getEmail())
                .fullName(clientEntity.getFullName())
                .typeOfIdentification(clientEntity.getTypeOfIdentification())
                .identification(clientEntity.getIdentification())
                .phone(clientEntity.getPhone())
                .civilStatus(clientEntity.getCivilStatus())
                .profession(clientEntity.getProfession())
                .address(clientEntity.getAddress())
                .houseNumber(clientEntity.getHouseNumber())
                .sector(clientEntity.getSector())
                .typeOfResidence(clientEntity.getTypeOfResidence())
                .state(clientEntity.isState())
                .createdAt(clientEntity.getCreatedAt())
                .updatedAt(clientEntity.getUpdatedAt())
                .build();
    }


    public static ClientsEntity clientAClientDto(Clients client) {
        return ClientsEntity.builder()
                .id(client.getId())
                .email(client.getEmail())
                .fullName(client.getFullName())
                .typeOfIdentification(client.getTypeOfIdentification())
                .identification(client.getIdentification())
                .phone(client.getPhone())
                .civilStatus(client.getCivilStatus())
                .profession(client.getProfession())
                .address(client.getAddress())
                .houseNumber(client.getHouseNumber())
                .sector(client.getSector())
                .typeOfResidence(client.getTypeOfResidence())
                .state(client.isState())
                .createdAt(client.getCreatedAt())
                .updatedAt(client.getUpdatedAt())
                .build();
    }

    public static ClientsEntity approveClient(Clients client) {
        PersonalReferenceEntity personalReference = PersonalReferenceEntity
                .builder()
                .id(client.getPersonalReference().getId())
                .interaction(client.getPersonalReference().getInteraction())
                .referred(client.getPersonalReference().getReferred())
                .build();
        personalReference.setReferenceEntity(client.getPersonalReference().getReference().stream().map(ele->ReferenceMapper.referenceAReferenceDto(ele,personalReference)).toList());

        return ClientsEntity.builder()
                .id(client.getId())
                .email(client.getEmail())
                .fullName(client.getFullName())
                .typeOfIdentification(client.getTypeOfIdentification())
                .identification(client.getIdentification())
                .phone(client.getPhone())
                .civilStatus(client.getCivilStatus())
                .profession(client.getProfession())
                .address(client.getAddress())
                .houseNumber(client.getHouseNumber())
                .sector(client.getSector())
                .typeOfResidence(client.getTypeOfResidence())
                .state(client.isState())
                .createdAt(client.getCreatedAt())
                .updatedAt(client.getUpdatedAt())
                .workingInformation(WorkingInformationEntity.builder()
                        .id(client.getWorkingInformation().getId())
                        .companyName(client.getWorkingInformation().getCompanyName())
                        .phone(client.getWorkingInformation().getPhone())
                        .address(client.getWorkingInformation().getAddress())
                        .timeWorking(client.getWorkingInformation().getTimeWorking())
                        .position(client.getWorkingInformation().getPosition())
                        .bossName(client.getWorkingInformation().getBossName())
                        .bossPhone(client.getWorkingInformation().getBossPhone())
                        .salary(client.getWorkingInformation().getSalary())
                        .paymentOfPayroll(client.getWorkingInformation().getPaymentOfPayroll())
                        .otherIncome(client.getWorkingInformation().getOtherIncome())
                        .description(client.getWorkingInformation().getDescription())
                        .build())
                .backAccount(BackAccountEntity.builder()
                        .id(client.getBankAccount().getId())
                        .accountType(client.getBankAccount().getAccountType())
                        .bank(client.getBankAccount().getBank())
                        .name(client.getBankAccount().getName())
                        .bankingApplication(client.getBankAccount().isBankingApplication())
                        .transfers(client.getBankAccount().isTransfers())
                        .accountNumber(client.getBankAccount().getAccountNumber())
                        .build())
                .personalReference(personalReference)
                .build();
    }



}
