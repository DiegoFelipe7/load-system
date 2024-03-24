package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.back.account;

import com.ddinnovations.loadsystem.domain.entity.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "back-account")
@Entity
public class BackAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private AccountType accountType;
    private String bank;
    private String name;
    private boolean bankingApplication;
    private boolean transfers;
    private String accountNumber;
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
