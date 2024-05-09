package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.user.mapper;

import com.ddinnovations.loadsystem.domain.entity.User;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.user.UserEntity;

public class UserMapper {
    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static User userDtoAUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .roles(userEntity.getRoles())
                .state(userEntity.isState())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }

    public static UserEntity userAUserDto(User user) {
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

    public static User userLogin(User user) {
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public static UserEntity userLoginEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
