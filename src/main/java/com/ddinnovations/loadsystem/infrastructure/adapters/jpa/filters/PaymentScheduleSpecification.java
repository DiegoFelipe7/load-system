package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.filters;

import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.paymentschedule.PaymentScheduleEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentScheduleSpecification implements Specification<PaymentScheduleEntity> {
    private String startDate;
    private String filterCriteriaText;
    private PaymentOfPayroll paymentCycle;

    @Override
    public Predicate toPredicate(Root<PaymentScheduleEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.orderBy(criteriaBuilder.asc(root.get("createdAt")));
        List<Predicate> predicates = new ArrayList<>();
        Predicate paymentStatus = criteriaBuilder.in(root.get("paymentStatus")).value(PaymentStatus.Pendiente).value(PaymentStatus.Mora);
        predicates.add(paymentStatus);
        if (StringUtils.hasText(getStartDate())) {
            Predicate filter = criteriaBuilder.equal(root.get("paymentDate"), getStartDate());
            predicates.add(filter);
        }

        if (StringUtils.hasText(getFilterCriteriaText())) {
            Predicate filter = criteriaBuilder.like(root.get("searchKey"), "%".concat(getFilterCriteriaText().toLowerCase()).concat("%"));
            predicates.add(filter);
        }

        if (getPaymentCycle() != null) {
            Predicate paymentCycle = criteriaBuilder.equal(root.get("paymentCycle"), getPaymentCycle());
            predicates.add(paymentCycle);
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
