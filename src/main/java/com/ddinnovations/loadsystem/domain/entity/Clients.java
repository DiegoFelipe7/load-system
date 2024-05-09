package com.ddinnovations.loadsystem.domain.entity;

import com.ddinnovations.loadsystem.domain.entity.enums.CivilStatus;
import com.ddinnovations.loadsystem.domain.entity.enums.TypeOfIdentification;
import com.ddinnovations.loadsystem.domain.entity.enums.TypeOfResidence;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class Clients {
    private String id;
    private String email;
    private String fullName;
    private TypeOfIdentification typeOfIdentification;
    private String identification;
    private String phone;
    private CivilStatus civilStatus;
    private String profession;
    private String address;
    private String houseNumber;
    private String sector;
    private String identificationCard;
    private String jobLetter;
    private String payrollStatements;
    private String city;
    private TypeOfResidence typeOfResidence;
    private String searchKey;
    private boolean state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime updatedAt;
    private WorkingInformation workingInformation;
    private BankAccount bankAccount;
    private PersonalReference personalReference;

}
