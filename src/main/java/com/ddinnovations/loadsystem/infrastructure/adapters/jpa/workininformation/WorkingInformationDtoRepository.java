package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.workininformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingInformationDtoRepository extends JpaRepository<WorkingInformationEntity, String>, QueryByExampleExecutor<WorkingInformationEntity>, JpaSpecificationExecutor<WorkingInformationEntity> {
}
