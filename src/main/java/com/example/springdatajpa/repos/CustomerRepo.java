package com.example.springdatajpa.repos;

import com.example.springdatajpa.entities.CustomerEntity;
import com.example.springdatajpa.models.CustomerOrmDto;
import com.example.springdatajpa.models.CustomerFieldsToStringProj;
import com.example.springdatajpa.models.CustomerResponseDto;
import com.example.springdatajpa.models.CustomerResponseProjection;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer>, JpaSpecificationExecutor<CustomerEntity> {


    @Query(nativeQuery = true)
    CustomerOrmDto datexample(@Param(value = "id")Integer id);
    // tuple or dtos explicit conversion
    @Query(nativeQuery = true, value = "select c.id,c.name,c.salary,c.doj from customer c where c.id=:cusId and c.salary=:cusSal")
    Optional<Tuple> nativeSqlFindCustomerByStringParams(@Param(value = "cusId") String id,
                                            @Param(value = "cusSal") String sal);

    @Query(value = "select c.customerId,c.customerName,c.customerSalary,c.customerDoj from CustomerEntity c" +
            " where c.customerId=:cusId and c.customerSalary=:cusSal")
    Optional<Tuple> jpqllFindCustomerByStringParams(@Param(value = "cusId") String id,
                                          @Param(value = "cusSal") String sal);

    @Query(value = "select c.customerId,c.customerName,c.customerSalary,cast(c.customerDoj as date) from CustomerEntity c" +
            " where c.customerId=:cusId")
    Tuple jpqlTuple(@Param(value = "cusId") Integer id);


    //projection implicit conversion
    @Query(value = "select c.id as cusId,c.salary as cusSalary,c.doj as cusDoj from customer c" +
            " where c.id=:cusId",nativeQuery = true)
    CustomerResponseProjection nativeSqlProjection(@Param(value = "cusId")Integer id);

    @Query(value = "select c.customerId as cusId ,c.customerSalary as cusSalary,cast(c.customerDoj as timestamp) as cusDoj " +
            " from CustomerEntity c where c.customerId=:cusId ")
    CustomerResponseProjection jpqlProjection(@Param(value = "cusId")Integer id);

    @Query(value = "select c.id as cusId,c.salary as cusSalary,c.doj as cusDoj from customer c" +
            " where c.id=:cusId",nativeQuery = true)
    CustomerFieldsToStringProj nativeSqlStringProjection(@Param(value = "cusId")Integer id);

    @Query(value = "select c.customerId as cusId ,c.customerSalary as cusSalary,cast(c.customerDoj as timestamp ) as cusDoj " +
            " from CustomerEntity c where c.customerId=:cusId ")
    CustomerFieldsToStringProj jpqlStringProjection(@Param(value = "cusId")Integer id);

    //method name derived queries | query methods
    Optional<CustomerEntity> findByCustomerIdAndCustomerSalary(Integer id, Double salary);

    @Query(value = "select c.* from customer c where cast(c.doj as date) between :from and :to ",nativeQuery = true)
    List<Tuple> nativeSqlfindByCustomerDojBetween(@Param(value = "from") String from,@Param(value = "to") String to);

    @Query(value = "select new com.example.springdatajpa.models.CustomerResponseDto(c.customerId,c.customerName,c.customerSalary," +
            " cast(c.customerDoj as date)) from CustomerEntity c where cast(c.customerDoj as date) between :from and :to " +
            "order by c.customerDoj desc")
    List<CustomerResponseDto> findByCustomerDojBetween(@Param(value = "from") 
                                                       LocalDate from, @Param(value = "to") LocalDate to);



}
