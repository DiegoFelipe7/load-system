package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.auth.mapper;

import com.ddinnovations.loadsystem.domain.entity.User;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.user.UserEntity;

public class AuthMapper {
    private AuthMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static User authDtoAuth(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .roles(userEntity.getRoles())
                .state(userEntity.isState())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }

    public static UserEntity authAuthDto(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .state(user.isState())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
