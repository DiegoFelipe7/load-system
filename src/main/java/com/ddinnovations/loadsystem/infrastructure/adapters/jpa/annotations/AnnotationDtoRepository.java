package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.annotations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface AnnotationDtoRepository extends JpaRepository<AnnotationEntity, String>, QueryByExampleExecutor<AnnotationEntity>, JpaSpecificationExecutor<AnnotationEntity> {

}