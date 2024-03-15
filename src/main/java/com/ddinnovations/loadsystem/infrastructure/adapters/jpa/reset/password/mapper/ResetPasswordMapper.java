package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.reset.password.mapper;

import com.ddinnovations.loadsystem.domain.entity.ResetPassword;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.reset.password.ResetPasswordEntity;


public class ResetPasswordMapper {

    private ResetPasswordMapper() {
        throw new IllegalStateException("Utility class");
    }


    public static ResetPassword resetPasswordDtoAResetPassword(ResetPasswordEntity resetPassword){
        return ResetPassword.builder()
                .id(resetPassword.getId())
                .email(resetPassword.getEmail())
                .token(resetPassword.getToken())
                .state(resetPassword.isState())
                .createdAt(resetPassword.getCreatedAt())
                .expirationDate(resetPassword.getExpirationDate())
                .build();
    }

    public static ResetPasswordEntity resetPasswordAResetPasswordDto(ResetPassword resetPassword){
        return ResetPasswordEntity.builder()
                .id(resetPassword.getId())
                .email(resetPassword.getEmail())
                .token(resetPassword.getToken())
                .state(resetPassword.isState())
                .createdAt(resetPassword.getCreatedAt())
                .expirationDate(resetPassword.getExpirationDate())
                .build();
    }

    public static ResetPasswordEntity buildResetPassword(String email){
        return ResetPasswordEntity.builder()
                .email(email)
                .build();
    }



}
