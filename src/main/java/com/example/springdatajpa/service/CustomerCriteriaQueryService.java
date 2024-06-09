package com.example.springdatajpa.service;

import com.example.springdatajpa.entities.CustomerEntity;
import com.example.springdatajpa.models.CustomerResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerCriteriaQueryService {

    private final EntityManager em;


    public List<CustomerResponseDto> getCustomersInfo(Map<String,String> queryParams){

        CriteriaBuilder cb = em.getCriteriaBuilder();;
        CriteriaQuery<Tuple> criteriaQuery=cb.createQuery(Tuple.class);
        Root<CustomerEntity> customerEntityRoot=criteriaQuery.from(CustomerEntity.class);
        criteriaQuery.multiselect(customerEntityRoot.get("customerId"),
                customerEntityRoot.get("customerName"),
                customerEntityRoot.get("customerSalary"),customerEntityRoot.get("customerDoj"));
        List<Predicate> predicates= queryParams.entrySet().stream()
                        .map(entry->{
                            if(entry.getKey().equals("customerDoj")){
                                return cb.equal(customerEntityRoot.get(entry.getKey()).as(LocalDate.class)
                                ,LocalDate.parse(entry.getValue(),DateTimeFormatter.BASIC_ISO_DATE));
                            }
                            else if(entry.getKey().equals("customerName")){
                                criteriaQuery.orderBy(cb.asc(customerEntityRoot.get("customerId")));
                                return cb.like(cb.upper(customerEntityRoot.get(entry.getKey())),"%"+entry.getValue()+"%");
                            }
                            else return cb.equal(customerEntityRoot.get(entry.getKey()),entry.getValue());
                        }).toList();
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Tuple> typedQuery=em.createQuery(criteriaQuery);
        List<Tuple> tuples=typedQuery.getResultList();
        List<CustomerResponseDto> customerResponseDtos=
                tuples.stream().map(tuple -> new CustomerResponseDto(tuple.get(0,Integer.class),tuple.get(1,String.class),
                                tuple.get(2,Double.class),tuple.get(3, LocalDateTime.class).toLocalDate())).collect(Collectors.toList());
        return customerResponseDtos;
    }
}
