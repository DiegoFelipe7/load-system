package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.workin.information.mapper;

import com.ddinnovations.loadsystem.domain.entity.WorkingInformation;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.workin.information.WorkingInformationEntity;

public class WorkingInformationMapper {
    private WorkingInformationMapper() {
        throw new IllegalStateException("Utility class");
    }


    public static WorkingInformation workingInformationDtoAWorkingInformation(WorkingInformationEntity workingInformation){
        return WorkingInformation.builder()
                .id(workingInformation.getId())
                .companyName(workingInformation.getCompanyName())
                .phone(workingInformation.getPhone())
                .address(workingInformation.getAddress())
                .timeWorking(workingInformation.getTimeWorking())
                .position(workingInformation.getPosition())
                .bossName(workingInformation.getBossName())
                .bossPhone(workingInformation.getBossPhone())
                .salary(workingInformation.getSalary())
                .paymentOfPayroll(workingInformation.getPaymentOfPayroll())
                .otherIncome(workingInformation.getOtherIncome())
                .description(workingInformation.getDescription())
                .createdAt(workingInformation.getCreatedAt())
                .updatedAt(workingInformation.getUpdatedAt())
                .build();
    }

    public static WorkingInformationEntity workingInformationAWorkingInformationDto(WorkingInformation workingInformation){
        return WorkingInformationEntity.builder()
                .id(workingInformation.getId())
                .companyName(workingInformation.getCompanyName())
                .phone(workingInformation.getPhone())
                .address(workingInformation.getAddress())
                .timeWorking(workingInformation.getTimeWorking())
                .position(workingInformation.getPosition())
                .bossName(workingInformation.getBossName())
                .bossPhone(workingInformation.getBossPhone())
                .salary(workingInformation.getSalary())
                .paymentOfPayroll(workingInformation.getPaymentOfPayroll())
                .otherIncome(workingInformation.getOtherIncome())
                .description(workingInformation.getDescription())
                .createdAt(workingInformation.getCreatedAt())
                .updatedAt(workingInformation.getUpdatedAt())
                //.client(ClientMapper.clientAClientDto(workingInformation.getClients()))
                .build();
    }
}
