package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.back.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BackAccountDtoRepository extends JpaRepository<BackAccountEntity, String>, QueryByExampleExecutor<BackAccountEntity> {
}
