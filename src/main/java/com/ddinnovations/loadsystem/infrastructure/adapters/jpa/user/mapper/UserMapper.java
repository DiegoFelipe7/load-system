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
                .fistName(userEntity.getFistName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                //.password(userEntity.getPassword())
                .roles(userEntity.getRoles())
                .state(userEntity.isState())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }

    public static UserEntity userAUserDto(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .fistName(user.getFistName())
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
