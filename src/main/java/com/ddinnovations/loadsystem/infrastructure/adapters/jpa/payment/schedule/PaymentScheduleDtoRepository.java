package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentScheduleDtoRepository extends JpaRepository<PaymentScheduleEntity, String>, QueryByExampleExecutor<PaymentScheduleEntity>, JpaSpecificationExecutor<PaymentScheduleEntity> {
    Optional<PaymentScheduleEntity> findByPaymentReference(String id);

    @Query("SELECT " +
            "    COALESCE(main.totalBalance, 0) AS totalBalance, " +
            "    COALESCE(sub.raisedMoney, 0) AS raisedMoney, " +
            "    COALESCE(sub.paymentsMade, 0) AS paymentsMade," +
            "    COALESCE(main.overduePayments, 0) AS overduePayments " +
            "FROM ( " +
            "    SELECT " +
            "        SUM(CASE WHEN ps.paymentStatus = 1 THEN ps.amount ELSE 0 END) AS totalBalance, " +
            "        SUM(CASE WHEN ps.paymentStatus = 2 THEN 1 ELSE 0 END) AS overduePayments " +
            "    FROM PaymentScheduleEntity ps " +
            ") AS main, " +
            "( " +
            "    SELECT " +
            "        SUM(CASE WHEN ps.paymentStatus = 1 THEN ps.amount ELSE 0 END) AS raisedMoney, " +
            "        SUM(CASE WHEN ps.paymentStatus = 1 THEN 1 ELSE 0 END) AS paymentsMade " +
            "    FROM PaymentScheduleEntity ps " +
            "    WHERE ps.createdAt BETWEEN :startDate AND :endDate " +
            ") AS sub")
    Object getIndicators(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    List<PaymentScheduleEntity> findAllByPaymentDate(String paymentDate);
}
