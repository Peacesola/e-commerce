package com.example.Ecommerce.controllers;


import com.example.Ecommerce.dtos.CustomerDto;
import com.example.Ecommerce.dtos.VendorDto;
import com.example.Ecommerce.models.CustomerModel;
import com.example.Ecommerce.models.VendorModel;
import com.example.Ecommerce.requests.CustomerLoginRequest;
import com.example.Ecommerce.requests.CustomerRegisterRequest;
import com.example.Ecommerce.requests.VendorLoginRequest;
import com.example.Ecommerce.requests.VendorRegisterRequest;
import com.example.Ecommerce.services.VendorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/restaurant")
public class VendorController {
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }


    @GetMapping("/getAllRestaurants")
    public List<VendorModel> getAllRestaurants() {
        return vendorService.getAllRestaurants();
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Map<String,Object>> getRestaurantById(@PathVariable Long id){
        VendorModel response = vendorService.getRestaurantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "status code",HttpStatus.OK.value(),
            "message","Restaurant registered successfully!",
            "restaurant", response
        ));//200
    }

    @PostMapping("/restaurantState/{id}/{toggle}")
    public ResponseEntity<Map<String,Object>> toggleRestaurantState(@PathVariable Long id,@PathVariable Boolean toggle){
        vendorService.toggleRestaurantState(id,toggle);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "message","State updated successfully!",
            "state",toggle
        ));
    }//200

    @GetMapping("/me")
    public ResponseEntity<Map<String,Object>> vendorMe(){
        VendorDto response = vendorService.loggedInUser();
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "status code",HttpStatus.OK.value(),
            "me", response
        ));
    }


}
