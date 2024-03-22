package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.filters;

import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule.PaymentScheduleEntity;
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
    private PaymentStatus paymentStatus;

    @Override
    public Predicate toPredicate(Root<PaymentScheduleEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        query.orderBy(criteriaBuilder.asc(root.get("createdAt")));
        Predicate filter = criteriaBuilder.notEqual(root.get("paymentStatus"), PaymentStatus.Cancelado);
        predicates.add(filter);
        if (StringUtils.hasText(getStartDate())) {
            Predicate filterByPaymentDate = criteriaBuilder.equal(root.get("paymentDate"), getStartDate());
            predicates.add(filterByPaymentDate);
        }

        if (StringUtils.hasText(getFilterCriteriaText())) {
            Predicate filterByText = criteriaBuilder.like(root.get("searchKey"), "%".concat(getFilterCriteriaText().toLowerCase()).concat("%"));
            predicates.add(filterByText);
        }

        if (getPaymentCycle() != null) {
            Predicate filterByPaymentCycle = criteriaBuilder.equal(root.get("paymentCycle"), getPaymentCycle());
            predicates.add(filterByPaymentCycle);
        }

        if (getPaymentStatus() != null) {
            Predicate status = criteriaBuilder.equal(root.get("paymentStatus"), getPaymentStatus());
            predicates.add(status);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
