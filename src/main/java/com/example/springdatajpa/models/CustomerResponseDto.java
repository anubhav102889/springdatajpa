package com.example.springdatajpa.models;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private Integer customerId;


    private String customerName;


    private Double customerSalary;


    private LocalDate customerDoj;

    public CustomerResponseDto(Integer customerId, String customerName, Double customerSalary, Date customerDoj) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerSalary = customerSalary;
        this.customerDoj = customerDoj.toLocalDate();
    }
}
