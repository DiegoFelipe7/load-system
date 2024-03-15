package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.workin.information;

import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.ClientsEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "working-information")
public class WorkingInformationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String companyName;
    private String phone;
    private String address;
    private int timeWorking;
    private String position;
    private String bossName;
    private String bossPhone;
    private BigDecimal salary;
    private PaymentOfPayroll paymentOfPayroll;
    private BigDecimal otherIncome;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @PrePersist()
    public void insert() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate()
    public void update() {
        this.updatedAt = LocalDateTime.now();
    }

}
