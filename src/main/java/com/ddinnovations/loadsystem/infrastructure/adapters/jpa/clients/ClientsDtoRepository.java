package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ClientsDtoRepository extends JpaRepository<ClientsEntity, String>, QueryByExampleExecutor<ClientsEntity>, JpaSpecificationExecutor<ClientsEntity> {
    boolean existsByIdentification(String id);

    Optional<ClientsEntity> findByIdentification(String id);
}
