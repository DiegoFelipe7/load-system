package com.ddinnovations.loadsystem.infrastructure.adapters.mongo.loan.application;

import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LoanApplicationDtoRepository extends MongoRepository<LoanApplicationEntity, String>, QueryByExampleExecutor<LoanApplicationEntity> {

    List<LoanApplicationEntity> findAllBy(Pageable pageable);

    List<LoanApplicationEntity> findAllBySearchKeyLike(Pageable pageable , String filterText);

    List<LoanApplicationEntity> findAllByPaymentCycle(Pageable pageable , PaymentOfPayroll paymentCycle);

    List<LoanApplicationEntity> findAllBySearchKeyLikeAndPaymentCycle(Pageable pageable , String filterText,PaymentOfPayroll paymentCycle);
    @Query("{ 'searchKey': { $regex: ?0, $options: 'i' }, 'paymentCycle': ?1}")
    List<LoanApplicationEntity> findBySearchKeyAndPaymentCycle(String searchKeyRegex, PaymentOfPayroll paymentCycle, Pageable pageable);

}
