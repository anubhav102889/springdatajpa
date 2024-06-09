package com.example.springdatajpa.controller;

import com.example.springdatajpa.models.CustomerResponseProjection;
import com.example.springdatajpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class CustomerController {


    private final CustomerService customerService;

    @GetMapping(value = "/customer/{id}")
    public ResponseEntity<Object> customerResponseProjection(@PathVariable(value = "id") Integer id,
                                                             @RequestParam(value = "strb", required = false) StringBuilder stringBuilder,
                                                             @RequestHeader HttpHeaders requestHeaders) {
        System.out.println("");
        System.out.println(requestHeaders);
        CustomerResponseProjection customerResponseProjection= customerService.customerResponseProjection(id);
        if(Objects.isNull(customerResponseProjection)){
            return ResponseEntity.ok("null");
        }
        return ResponseEntity.ok()
                .body(customerResponseProjection);
    }
}
