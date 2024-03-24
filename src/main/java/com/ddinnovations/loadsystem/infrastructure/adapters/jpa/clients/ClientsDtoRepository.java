package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ClientsDtoRepository extends JpaRepository<ClientsEntity, String>, QueryByExampleExecutor<ClientsEntity>, JpaSpecificationExecutor<ClientsEntity> {
    boolean existsByIdentification(String id);

    Optional<ClientsEntity> findByIdentification(String id);

    @Query(nativeQuery = true, value = "SELECT " +
            "(SELECT COUNT(*) FROM clients c1) AS totalClients, " +
            "(SELECT COUNT(*) FROM clients c2 WHERE c2.created_at BETWEEN :startDate AND :endDate) AS totalNewClients")
    Object getIndicators(LocalDateTime startDate, LocalDateTime endDate);





}
