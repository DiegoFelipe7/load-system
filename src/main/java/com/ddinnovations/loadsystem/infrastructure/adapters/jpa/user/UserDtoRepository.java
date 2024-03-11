package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDtoRepository  extends JpaRepository<UserEntity,String>, QueryByExampleExecutor<UserEntity>  , JpaSpecificationExecutor<UserEntity> {
    boolean existsByEmailIgnoreCase(String email);
}
