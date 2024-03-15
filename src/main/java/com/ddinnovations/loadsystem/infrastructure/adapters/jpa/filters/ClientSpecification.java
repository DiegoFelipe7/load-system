package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.filters;

import com.ddinnovations.loadsystem.domain.entity.enums.OrderBy;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.ClientsEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.GenerateDates;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientSpecification implements Specification<ClientsEntity> {
    private String filterCriteriaText;
    private OrderBy orderBy;
    private String startDate;
    private String endDate;


    @Override
    public Predicate toPredicate(Root<ClientsEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String createdAt = "createdAt";
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.hasText(getFilterCriteriaText())) {
            Predicate filter = criteriaBuilder.like(root.get("searchKey"), "%".concat(getFilterCriteriaText().toLowerCase()).concat("%"));
            predicates.add(filter);
        }
        //TODO:NO FUNCIONA
        if (orderBy != null) {
            query.orderBy(orderBy.equals(OrderBy.DESC) ?
                    criteriaBuilder.desc(root.<LocalDateTime>get(createdAt)) :
                    criteriaBuilder.asc(root.<LocalDateTime>get(createdAt))
            );
        }
        if (StringUtils.hasText(startDate) && StringUtils.hasText(endDate)) {
            Predicate dateRange = criteriaBuilder.between(root.get(createdAt), GenerateDates.starDate(getStartDate()), GenerateDates.endDate(getEndDate()));
            predicates.add(dateRange);
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
