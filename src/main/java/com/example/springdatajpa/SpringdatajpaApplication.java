package com.example.springdatajpa;

import com.example.springdatajpa.entities.CustomerEntity;
import com.example.springdatajpa.models.CustomerOrmDto;
import com.example.springdatajpa.models.CustomerResponseDto;
import com.example.springdatajpa.repos.CustomerRepo;
import com.example.springdatajpa.repos.UserRepo;
import com.example.springdatajpa.service.CustomerCriteriaQueryService;
import com.example.springdatajpa.specifications.CustomerSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringdatajpaApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(SpringdatajpaApplication.class, args);
        System.out.println("start");
        CustomerCriteriaQueryService customerCriteriaQueryService = applicationContext.getBean(CustomerCriteriaQueryService.class);
        CustomerRepo customerRepo = applicationContext.getBean(CustomerRepo.class);
        UserRepo userRepo = applicationContext.getBean(UserRepo.class);
        ObjectMapper objectMapper=applicationContext.getBean(ObjectMapper.class);
        String regex = "^[1-9]{1}\\d{1,4}$";
        CustomerOrmDto customerOrmDto=new CustomerOrmDto();
        customerOrmDto.setCustomerId(1);
        customerOrmDto.setCustomerName("A");
        customerOrmDto.setCustomerDoj(LocalDate.now());


        try {

            String jsonString = objectMapper.writeValueAsString(customerOrmDto);
            System.out.println(jsonString);
            CustomerOrmDto customerOrmDtoFromObjectMapper=objectMapper.readValue(jsonString,CustomerOrmDto.class);
            System.out.println(customerOrmDtoFromObjectMapper);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        String s="anu";
        System.out.println(s);

//
//        //CusomerOrmDto cusomerOrmDto=customerRepo.datexample(1);
//        //criteriaQuery
//        List<CustomerResponseDto> customerResponseDtos = customerCriteriaQueryService.getCustomersInfo(Map.of("customerDoj", "20241010",
//                "customerId", "1","customerSalary","31999.99"));
//        System.out.println(customerResponseDtos);
//
        //specification
//        List<CustomerEntity> customerEntities = customerRepo.findAll(new CustomerSpecification(Map.of("customerDoj", "2024-10-10",
//                "customerSalary", "31999.99","customerId", "1")));
//        System.out.println(customerEntities);

//        Optional<CustomerEntity> optionalCustomerEntity=customerRepo.findByCustomerIdAndCustomerSalary(1,31999.99);
//        CustomerEntity customerEntity=optionalCustomerEntity.orElse(null);
//        System.out.println(customerEntity);
////
//        // explicit conversion
//        Optional<Tuple> optionalTupleNativeSql = customerRepo.nativeSqlFindCustomerByStringParams("1", "31999.99");
//        CustomerResponseDto nativeCustomerResponseDto = optionalTupleNativeSql.map(tuple -> new CustomerResponseDto(tuple.get(0, Integer.class),
//                tuple.get(1, String.class), tuple.get(2, BigDecimal.class).doubleValue(), tuple.get(3, Timestamp.class).toLocalDateTime().toLocalDate())).orElse(null);
//        System.out.println(nativeCustomerResponseDto);
//
////        Optional<Tuple> optionalTupleJpql = customerRepo.jpqllFindCustomerByStringParams("1", "31999.99");
////        CustomerResponseDto jpqlCustomerResponseDto = optionalTupleJpql.map(tuple -> new CustomerResponseDto(tuple.get(0, Integer.class),
////                tuple.get(1, String.class), tuple.get(2, Double.class), tuple.get(3, LocalDateTime.class).toLocalDate())).orElse(null);
////        System.out.println(jpqlCustomerResponseDto);
//
//        Tuple jpqlTuple = customerRepo.jpqlTuple(1);
//        CustomerResponseDto jpqlCustomerResponseDto2 = new CustomerResponseDto(jpqlTuple.get(0, Integer.class),
//                jpqlTuple.get(1, String.class), jpqlTuple.get(2, Double.class), jpqlTuple.get(3, Date.class).toLocalDate());
//        System.out.println(jpqlCustomerResponseDto2);
//
//        //native projection implicit conversion
//        CustomerResponseProjection nativeSqlProjection = customerRepo.nativeSqlProjection(1);
//        System.out.println(String.format("nativeSqlProjection:: cusid-%s, cussalary-%s, cusDoj-%s ", nativeSqlProjection.getCusId(),
//                nativeSqlProjection.getCusSalary(), nativeSqlProjection.getCusDoj()));
//
//        CustomerResponseProjection jpqlProjection = customerRepo.jpqlProjection(1);
//        System.out.println(String.format("jpqlProjection:: cusid-%s, cussalary-%s, cusDoj-%s ", jpqlProjection.getCusId(),
//                jpqlProjection.getCusSalary(), jpqlProjection.getCusDoj()));
//
//        CustomerFieldsToStringProj nativeSqlCustomerFieldsToStringProj= customerRepo.nativeSqlStringProjection(1);
//        System.out.println(String.format("nativeSqlCustomerFieldsToStringProj:: cusid-%s, cussalary-%s, cusDoj-%s ", nativeSqlCustomerFieldsToStringProj.getCusId(),
//                nativeSqlCustomerFieldsToStringProj.getCusSalary(), nativeSqlCustomerFieldsToStringProj.getCusDoj()));
//
//        CustomerFieldsToStringProj jpqlCustomerFieldsToStringProj= customerRepo.jpqlStringProjection(1);
//        System.out.println(String.format("jpqlCustomerFieldsToStringProj:: cusid-%s, cussalary-%s, cusDoj-%s ", jpqlCustomerFieldsToStringProj.getCusId(),
//                jpqlCustomerFieldsToStringProj.getCusSalary(), jpqlCustomerFieldsToStringProj.getCusDoj()));
//
//        List<CustomerResponseDto> customerResponseDtosBetween= customerRepo.findByCustomerDojBetween(
//                LocalDate.parse("20241001",DateTimeFormatter.BASIC_ISO_DATE),
//                        LocalDate.parse("20241031",DateTimeFormatter.BASIC_ISO_DATE));
//        System.out.println(customerResponseDtosBetween);

    }

}
