package com.example.springdatajpa.mapper;

import com.example.springdatajpa.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,imports = {LocalDate.class, DateTimeFormatter.class})
public interface MapperEx {


     CustomerTarget customerTarget(CustomerSource customerSource, List<AddressSource> addresses);

    // @Mapping(target = "customerDoj",dateFormat = "dd-MM-yyyy",defaultExpression = "java(LocalDate.now().format(DateTimeFormatter.ofPattern(\"dd-MM-yyyy\")))")
     CustomerOrmDto customerOrmDto(CustomerResponseDto customerResponseDto);
}
