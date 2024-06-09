package com.example.springdatajpa.models;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
//@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrmDto {

    private Integer customerId;

    private String customerName;

    private LocalDate customerDoj;
}
