package com.example.Ecommerce.controllers;


import com.example.Ecommerce.dtos.CustomerDto;
import com.example.Ecommerce.dtos.VendorDto;
import com.example.Ecommerce.requests.CustomerLoginRequest;
import com.example.Ecommerce.requests.CustomerRegisterRequest;
import com.example.Ecommerce.requests.VendorLoginRequest;
import com.example.Ecommerce.requests.VendorRegisterRequest;
import com.example.Ecommerce.responses.CustomerResponse;
import com.example.Ecommerce.responses.VendorResponse;
import com.example.Ecommerce.services.CustomerService;
import com.example.Ecommerce.services.VendorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final CustomerService service;
    private final VendorService vendorService;

    public AuthController(CustomerService service, VendorService vendorService) {
        this.service = service;
        this.vendorService = vendorService;
    }

    @PostMapping("/customer/register")
    public ResponseEntity<Map<String,Object>> registerCustomer (@RequestBody @Valid CustomerRegisterRequest request) {
        CustomerResponse response = service.registerCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
            "status code",HttpStatus.CREATED.value(),
            "message","Customer registered successfully!",
            "customer", response
        ));
    }
    @PostMapping("/customer/login")
    public ResponseEntity<Map<String,Object>> loginCustomer (@RequestBody @Valid CustomerLoginRequest request) {
        CustomerDto response = service.loginCustomer(request);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "status code",HttpStatus.OK.value(),
            "message","Customer logged in successfully!",
            "customer", response
        ));
    }



    @PostMapping("/restaurant/register")
    public ResponseEntity<Map<String,Object>> registerRestaurant (@RequestBody @Valid VendorRegisterRequest request) {
        VendorResponse response = vendorService.registerRestaurant(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
            "status code",HttpStatus.CREATED.value(),
            "message","Restaurant registered successfully!",
            "restaurant", response
        ));// 201
    }

    @PostMapping("/restaurant/login")
    public ResponseEntity<Map<String,Object>> loginVendor (@RequestBody @Valid VendorLoginRequest request) {
        VendorDto response = vendorService.loginVendor(request);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "status code",HttpStatus.OK.value(),
            "message","Vendor logged in successfully!",
            "customer", response
        ));
    }


}
