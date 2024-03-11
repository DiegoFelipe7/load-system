package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanDtoRepository extends JpaRepository<LoanEntity,String>, QueryByExampleExecutor<LoanEntity>, JpaSpecificationExecutor<LoanEntity> {
    List<LoanEntity> findAllBy(PageRequest pageRequest);
}
