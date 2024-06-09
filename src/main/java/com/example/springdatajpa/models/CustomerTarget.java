package com.example.springdatajpa.models;


import lombok.Data;

import java.util.List;

@Data
public class CustomerTarget {

    private Integer customerId;

    private String customerName;

    List<AddressTarget> addresses;

}
