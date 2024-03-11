package com.ddinnovations.loadsystem.domain.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ResetPassword {
    private String id;
    private String email;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expirationDate;
    private boolean state;
}
