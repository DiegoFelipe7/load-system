package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.resetpassword;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "reset-password")
public class ResetPasswordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expirationDate;
    private boolean state;


    @PrePersist()
    public void insert() {
        this.state = true;
        this.token = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.expirationDate = LocalDateTime.now().minusHours(2);
    }

}
