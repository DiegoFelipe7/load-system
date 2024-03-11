package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.filters;

import com.ddinnovations.loadsystem.domain.entity.enums.OrderBy;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.ClientsEntity;
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
@ToString
public class ClientSpecification implements Specification<ClientsEntity> {
    private String filterCriteriaText;
    private String identification;
    private OrderBy orderBy;

    @Override
    public Predicate toPredicate(Root<ClientsEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.hasText(getFilterCriteriaText())) {
            Predicate filter = criteriaBuilder.like(root.get("searchKey"), "%".concat(getFilterCriteriaText().toLowerCase()).concat("%"));
            predicates.add(filter);
        }
        if (StringUtils.hasText(getIdentification())) {
            Predicate filter = criteriaBuilder.like(root.get("identification"), "%".concat(getIdentification()).concat("%"));
            predicates.add(filter);
        }
        if (orderBy != null) {
            query.orderBy(orderBy.equals(OrderBy.DESC) ? criteriaBuilder.desc(root.get("createdAt")) : criteriaBuilder.asc(root.get("createdAt")));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
