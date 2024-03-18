package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface LoanDtoRepository extends JpaRepository<LoanEntity, String>, QueryByExampleExecutor<LoanEntity>, JpaSpecificationExecutor<LoanEntity> {
    @Query(nativeQuery = true, value =
            "SELECT " +
                    "    SUM(CASE WHEN l.loan_state IN (1, 3) THEN l.amount ELSE 0 END) AS totalInvestedCapital, " +
                    "    SUM(CASE WHEN l.loan_state IN (1, 3) THEN l.amount ELSE 0 END) AS investedCapital, " +
                    "    SUM(CASE WHEN l.loan_state IN (1) THEN 1 ELSE 0 END) AS totalActiveLoans, " +
                    "    SUM(CASE WHEN l.loan_state IN (1) THEN 1 ELSE 0 END) AS activeLoans, " +
                    "    SUM(CASE WHEN l.loan_state IN (3) THEN 1 ELSE 0 END) AS totalLoansPaid, " +
                    "    SUM(CASE WHEN l.loan_state IN (3) THEN 1 ELSE 0 END) AS loansPaid " +
                    "FROM " +
                    "    loan l " +
                    "WHERE " +
                    "    (l.loan_state IN (1, 3) OR l.created_at BETWEEN :startDate AND :endDate)")
    Object getIndicators(LocalDateTime startDate, LocalDateTime endDate);


    @Query(nativeQuery = true, value =
            "SELECT " +
                    "    SUM(CASE WHEN l.loan_state IN (1, 3) THEN l.amount ELSE 0 END) AS totalInvestedCapital," +
                    "    SUM(CASE WHEN l.loan_state IN (1) THEN 1 ELSE 0 END) AS totalActiveLoans, " +
                    "    SUM(CASE WHEN l.loan_state IN (1, 3) THEN l.earnings ELSE 0 END) AS totalProfits, " +
                    "    SUM(CASE WHEN l.loan_state IN (3) THEN 1 ELSE 0 END) AS loansPaid " +
                    "FROM " +
                    "    loan l ")
    Object getControlPanelIndicators();
}
