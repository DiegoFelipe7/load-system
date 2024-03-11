package com.ddinnovations.loadsystem.infrastructure.adapters.mongo.loanapplication;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface LoanApplicationDtoRepository extends MongoRepository<LoanApplicationEntity, String> {
    List<LoanApplicationEntity> findAllBy(Pageable pageable);
}
