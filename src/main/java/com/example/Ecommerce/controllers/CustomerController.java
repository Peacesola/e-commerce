package com.example.Ecommerce.controllers;

import com.example.Ecommerce.dtos.CustomerDto;
import com.example.Ecommerce.models.CustomerModel;
import com.example.Ecommerce.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    CustomerService service;
    public CustomerController(CustomerService service) {
        this.service = service;
    }


    @GetMapping("/getAllCustomers")
    public List<CustomerModel> getAllCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String,Object>> customerMe (){
        CustomerDto response = service.loggedInUser();
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "status code",HttpStatus.OK.value(),
            "me", response
        ));
    }
}
