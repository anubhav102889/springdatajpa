package com.example.springdatajpa.models;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;

public interface CustomerResponseProjection {

  BigDecimal getCusId();

  BigInteger getCusSalary();

  LocalDate getCusDoj();
}
