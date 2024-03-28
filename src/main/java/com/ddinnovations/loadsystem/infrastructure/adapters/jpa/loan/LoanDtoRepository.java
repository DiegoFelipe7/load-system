package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface LoanDtoRepository extends JpaRepository<LoanEntity, String>, QueryByExampleExecutor<LoanEntity>, JpaSpecificationExecutor<LoanEntity> {
   @Query("SELECT " +
           " COALESCE(main.totalInvestedCapital, 0) AS totalInvestedCapital, " +
           " COALESCE(sub.investedCapital, 0) AS investedCapital, " +
           " COALESCE(main.totalActiveLoans, 0) AS totalActiveLoans, " +
           " COALESCE(sub.activeLoans, 0) AS activeLoans, " +
           " COALESCE(sub.totalLoansPaid, 0) AS totalLoansPaid, " +
           " COALESCE(main.loansPaid, 0) AS loansPaid " +
           "FROM ( " +
               "SELECT " +
                   "SUM(CASE WHEN l.loanState IN (1, 3) THEN l.amount ELSE 0 END) AS totalInvestedCapital, " +
                   "SUM(CASE WHEN l.loanState = 1 THEN 1 ELSE 0 END) AS totalActiveLoans, " +
                   "SUM(CASE WHEN l.loanState = 3 THEN 1 ELSE 0 END) AS loansPaid " +
               "FROM LoanEntity  l" +
           ") AS main," +
           "( " +
           "SELECT " +
                "SUM(CASE WHEN l.loanState IN (1, 3) THEN l.amount ELSE 0 END) AS investedCapital, " +
                "SUM(CASE WHEN l.loanState = 1 THEN 1 ELSE 0 END) AS activeLoans, " +
                "SUM(CASE WHEN l.loanState = 3 THEN 1 ELSE 0 END) AS totalLoansPaid " +
           "FROM LoanEntity l" +
           " WHERE l.createdAt BETWEEN :startDate AND :endDate ) AS sub")
   Object getIndicators(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


    @Query("SELECT " +
            "    COALESCE(SUM(CASE WHEN l.loanState IN (1,3) THEN l.amount ELSE 0 END), 0) AS totalInvestedCapital, " +
            "    COALESCE(SUM(CASE WHEN l.loanState = 1 THEN 1 ELSE 0 END), 0) AS totalActiveLoans, " +
            "    COALESCE(SUM(CASE WHEN l.loanState IN (1,3) THEN l.earnings ELSE 0 END), 0) AS totalProfits," +
            "    COALESCE(SUM(CASE WHEN l.loanState = 3 THEN 1 ELSE 0 END), 0) AS loansPaid" +
            " FROM LoanEntity l")
    Object getLoanStatistics();

}
