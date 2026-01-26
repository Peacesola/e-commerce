package com.example.Ecommerce.services;

import com.example.Ecommerce.dtos.CustomerDto;
import com.example.Ecommerce.exceptions.UserAlreadyExistsException;
import com.example.Ecommerce.models.CustomerModel;
import com.example.Ecommerce.repositories.VendorRepository;
import com.example.Ecommerce.requests.CustomerLoginRequest;
import com.example.Ecommerce.requests.CustomerRegisterRequest;
import com.example.Ecommerce.repositories.CustomerRepository;
import com.example.Ecommerce.responses.CustomerResponse;
import com.example.Ecommerce.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    private final AuthenticationManager manager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    public CustomerService(CustomerRepository customerRepository, VendorRepository vendorRepository, AuthenticationManager manager, JwtService jwtService, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
        this.manager = manager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerModel findCustomerById(Long id){
        return customerRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Customer Not Found"));
    }

    public CustomerResponse registerCustomer(CustomerRegisterRequest request){
       CustomerModel customerModel =  CustomerModel.builder().
           email(request.getEmail())
           .password(passwordEncoder.encode(request.getPassword()))
           .build();
       if(customerRepository.existsByEmail(request.getEmail())||
       vendorRepository.existsByEmail(request.getEmail())
       ){
           throw new UserAlreadyExistsException("There is already an account with that email");
       }

       CustomerModel savedUser = customerRepository.save(customerModel);
        return CustomerResponse.builder()
            .id(savedUser.getId())
            .email(savedUser.getEmail())
            .build();

    }

    public CustomerDto loginCustomer(CustomerLoginRequest request){
        manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails userDetails= userDetailsService.loadUserByUsername(request.getEmail());
        CustomerModel model=customerRepository.findByEmail(request.getEmail()).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        String token= jwtService.generateToken(model.getEmail());

       return CustomerDto.builder()
            .email(userDetails.getUsername())
            .id(model.getId())
           .token(token)
            .build();
    }


    public CustomerDto loggedInUser() {
        Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        CustomerModel model= customerRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return CustomerDto.builder()
            .email(email)
            .id(model.getId())
            .build();

    }

    public List<CustomerModel> getAllCustomers(){
         return customerRepository.findAll();
    }


}
