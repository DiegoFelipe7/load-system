package com.ddinnovations.loadsystem.infrastructure.adapters.mongo.loan.application;

import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface LoanApplicationDtoRepository extends MongoRepository<LoanApplicationEntity, String>, QueryByExampleExecutor<LoanApplicationEntity> {

    List<LoanApplicationEntity> findAllBy(Pageable pageable);

    List<LoanApplicationEntity> findAllBySearchKeyLikeAndPaymentCycle(String searchKey, PaymentOfPayroll paymentCycle, Pageable pageable);

}
