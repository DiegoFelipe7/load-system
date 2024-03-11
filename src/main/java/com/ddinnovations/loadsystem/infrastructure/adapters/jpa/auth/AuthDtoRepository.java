package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.auth;

import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface AuthDtoRepository extends JpaRepository<UserEntity,String>, QueryByExampleExecutor<UserEntity> {
    Optional<UserEntity> findByEmailEqualsIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
}
