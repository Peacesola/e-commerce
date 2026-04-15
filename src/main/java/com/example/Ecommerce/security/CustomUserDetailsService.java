package com.example.Ecommerce.security;

import com.example.Ecommerce.exceptions.ProductNotFoundException;
import com.example.Ecommerce.models.CustomerModel;
import com.example.Ecommerce.models.VendorModel;
import com.example.Ecommerce.repositories.CustomerRepository;
import com.example.Ecommerce.repositories.VendorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    public CustomUserDetailsService(CustomerRepository customerRepository,VendorRepository vendorRepository) {
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Optional<CustomerModel> customerModel= customerRepository.findByEmail(email)/*.orElseThrow(()-> new UsernameNotFoundException("User not found"))*/;
        if(customerModel.isPresent()) {
            CustomerModel customer = customerModel.get();
            return new org.springframework.security.core.userdetails.User(
                customer.getEmail(),
                customer.getPassword(),
                Collections.emptyList()
            );
        }
        Optional<VendorModel> vendorModel= vendorRepository.findByEmail(email)/*.orElseThrow(()-> new UsernameNotFoundException("Vendor not found"))*/;
          if(vendorModel.isPresent()) {
              VendorModel vendor = vendorModel.get();
              return new org.springframework.security.core.userdetails.User(
                  vendor.getEmail(),
                  vendor.getPassword(),
                  Collections.emptyList()
              );
          }
          throw new UsernameNotFoundException("User not found");
    }
}
