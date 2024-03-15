package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.paymentschedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentScheduleDtoRepository extends JpaRepository<PaymentScheduleEntity, String>, QueryByExampleExecutor<PaymentScheduleEntity>, JpaSpecificationExecutor<PaymentScheduleEntity> {

}
