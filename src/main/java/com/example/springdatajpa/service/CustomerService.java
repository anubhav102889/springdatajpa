package com.example.springdatajpa.service;

import com.example.springdatajpa.models.CustomerResponseProjection;
import com.example.springdatajpa.repos.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerResponseProjection customerResponseProjection(Integer id){
        return customerRepo.nativeSqlProjection(id);
    }
}
