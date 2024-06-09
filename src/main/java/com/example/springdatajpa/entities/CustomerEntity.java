package com.example.springdatajpa.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Data
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer customerId;

    @Column(name = "name")
    private String customerName;

    @Column(name = "salary")
    private Double customerSalary;

    @Column(name = "doj")
    private LocalDateTime customerDoj;
}
