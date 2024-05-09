package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.filters;

import com.ddinnovations.loadsystem.domain.entity.enums.OrderBy;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.annotations.AnnotationEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.GenerateDates;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnotationSpecification implements Specification<AnnotationEntity> {
    private String id;
    private String filterCriteriaText;
    private OrderBy orderBy;
    private String startDate;
    private String endDate;

    @Override
    public Predicate toPredicate(Root<AnnotationEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String createdAt = "createdAt";
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(getFilterCriteriaText())) {
            Predicate filterByText = criteriaBuilder.like(root.get("searchKey"), "%".concat(getFilterCriteriaText().toLowerCase()).concat("%"));
            predicates.add(filterByText);
        }

        if (StringUtils.hasText(getId())) {
            Predicate filterId = criteriaBuilder.equal(root.get("loanId"), getId());
            predicates.add(filterId);
        }

        if (getOrderBy() != null) {
            query.orderBy(orderBy.equals(OrderBy.DESC) ?
                    criteriaBuilder.desc(root.<LocalDateTime>get(createdAt)) :
                    criteriaBuilder.asc(root.<LocalDateTime>get(createdAt)));
        }

        if (StringUtils.hasText(getStartDate()) && StringUtils.hasText(getEndDate())) {
            Predicate dateRangePredicate = criteriaBuilder.between(root.get(createdAt), GenerateDates.starDate(getStartDate()), GenerateDates.endDate(getEndDate()));
            predicates.add(dateRangePredicate);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
