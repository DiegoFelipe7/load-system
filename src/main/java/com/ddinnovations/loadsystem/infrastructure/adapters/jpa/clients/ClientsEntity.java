package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients;


import com.ddinnovations.loadsystem.domain.entity.enums.CivilStatus;
import com.ddinnovations.loadsystem.domain.entity.enums.TypeOfIdentification;
import com.ddinnovations.loadsystem.domain.entity.enums.TypeOfResidence;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.back.account.BackAccountEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.LoanEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.personal.reference.PersonalReferenceEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.workin.information.WorkingInformationEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "clients")
@Entity
@ToString
public class ClientsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    private TypeOfResidence typeOfResidence;
    private String searchKey;
    private boolean state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToOne(cascade = CascadeType.ALL)
    private WorkingInformationEntity workingInformation;
    @OneToOne(cascade = CascadeType.ALL)
    private BackAccountEntity backAccount;
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private PersonalReferenceEntity personalReference;
    @OneToMany(targetEntity = LoanEntity.class, fetch = FetchType.LAZY, mappedBy = "client")
    private List<LoanEntity> loanEntities;


    @PrePersist()
    public void insert() {
        this.state = true;
        this.searchKey = (this.fullName + '|' + this.state + '|' + this.identification + '|' + this.email + '|' + this.phone).toLowerCase();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate()
    public void update() {
        this.searchKey = (this.fullName + '|' + this.state + '|' + this.identification + '|' + this.email + '|' + this.phone).toLowerCase();
        this.updatedAt = LocalDateTime.now();
    }
}
