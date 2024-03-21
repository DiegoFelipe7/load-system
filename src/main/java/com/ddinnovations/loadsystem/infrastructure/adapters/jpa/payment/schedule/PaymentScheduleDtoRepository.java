package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PaymentScheduleDtoRepository extends JpaRepository<PaymentScheduleEntity, String>, QueryByExampleExecutor<PaymentScheduleEntity>, JpaSpecificationExecutor<PaymentScheduleEntity> {
    @Query(nativeQuery = true, value =
            "SELECT " +
                    "    SUM(CASE WHEN ps.payment_status = 1 THEN ps.amount ELSE 0 END) AS totalBalance, " +
                    "    SUM(CASE WHEN ps.payment_status = 1 THEN ps.amount ELSE 0 END) AS raisedMoney, " +
                    "    SUM(CASE WHEN ps.payment_status = 1 THEN 1 ELSE 0 END) AS paymentsMade, " +
                    "    SUM(CASE WHEN ps.payment_status = 2 THEN 1 ELSE 0 END) AS overduePayments " +
                    "FROM " +
                    "    payment_schedule ps")
    Object getIndicators(LocalDateTime startDate, LocalDateTime endDate);
}
