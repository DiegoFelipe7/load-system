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
        query.orderBy(criteriaBuilder.desc(root.get("createdAt")));
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.hasText(getFilterCriteriaText())) {
            Predicate filterByText = criteriaBuilder.like(root.get("searchKey"), "%".concat(getFilterCriteriaText().toLowerCase()).concat("%"));
            predicates.add(filterByText);
        }

        if (getPaymentMethod() != null) {
            Predicate paymentMethodPredicate = criteriaBuilder.equal(root.get("paymentCycle"), getPaymentMethod());
            predicates.add(paymentMethodPredicate);
        }

        if (getLoanState() != null) {
            Predicate loanStatePredicate = criteriaBuilder.equal(root.get("loanState"), getLoanState());
            predicates.add(loanStatePredicate);
        }
        if (StringUtils.hasText(getStartDate())) {
            Predicate dateRangePredicate = criteriaBuilder.between(root.get("createdAt"), GenerateDates.starDate(getStartDate()), GenerateDates.endDate(getStartDate()));
            predicates.add(dateRangePredicate);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
