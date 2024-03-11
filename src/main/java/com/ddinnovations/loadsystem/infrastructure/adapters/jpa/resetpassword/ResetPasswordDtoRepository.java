package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.resetpassword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResetPasswordDtoRepository extends JpaRepository<ResetPasswordEntity, String> {
    Optional<ResetPasswordEntity> findByToken(String token);
}
