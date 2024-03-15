package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.filters;

import com.ddinnovations.loadsystem.domain.entity.enums.LoanState;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.GenerateDates;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.LoanEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanSpecification implements Specification<LoanEntity> {
    private String filterCriteriaText;
    private PaymentOfPayroll paymentMethod;
    private LoanState loanState;
    private String startDate;

    @Override
    public Predicate toPredicate(Root<LoanEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.orderBy(criteriaBuilder.asc(root.get("createdAt")));
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.hasText(getFilterCriteriaText())) {
            Predicate filter = criteriaBuilder.like(root.get("searchKey"), "%".concat(getFilterCriteriaText().toLowerCase()).concat("%"));
            predicates.add(filter);
        }

        if (getPaymentMethod() != null) {
            Predicate paymentStatus = criteriaBuilder.equal(root.get("paymentCycle"), getPaymentMethod());
            predicates.add(paymentStatus);
        }

        if (getLoanState() != null) {
            Predicate paymentStatus = criteriaBuilder.equal(root.get("loanState"), getLoanState());
            predicates.add(paymentStatus);
        }
       if (StringUtils.hasText(startDate)) {
            Predicate dateRange = criteriaBuilder.between(root.get("createdAt"), GenerateDates.starDate(getStartDate()),GenerateDates.endDate(getStartDate()));
            predicates.add(dateRange);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
