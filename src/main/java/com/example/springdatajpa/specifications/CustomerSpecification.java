package com.example.springdatajpa.specifications;

import com.example.springdatajpa.entities.CustomerEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomerSpecification implements Specification<CustomerEntity> {
    
    private final Map<String,String> queryParams;

    @Override
    public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates= queryParams.entrySet().stream()
                .map(entry->{
                    if(entry.getKey().equals("customerDoj")){
                        return criteriaBuilder.equal(root.get(entry.getKey()).as(LocalDate.class)
                                ,LocalDate.parse(entry.getValue()));
                    }
                    else if(entry.getKey().equals("customerName")){
                        query.orderBy(criteriaBuilder.asc(root.get("customerId")));
                        return criteriaBuilder.like(criteriaBuilder.upper(root.get(entry.getKey())),"%"+entry.getValue()+"%");
                    }
                    else return criteriaBuilder.equal(root.get(entry.getKey()),entry.getValue());
                }).toList();
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
