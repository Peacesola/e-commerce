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
        CustomerModel customerModel= customerRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        if(customerRepository.existsByEmail(email)) {
            return new org.springframework.security.core.userdetails.User(
                customerModel.getEmail(),
                customerModel.getPassword(),
                Collections.emptyList()
            );
        }
        VendorModel vendorModel= vendorRepository.findByRestaurantName(email).orElseThrow(()-> new UsernameNotFoundException("Vendor not found"));
          if(vendorRepository.existsByRestaurantName(email)) {
              return new org.springframework.security.core.userdetails.User(
                  vendorModel.getEmail(),
                  vendorModel.getPassword(),
                  Collections.emptyList()
              );
          }
          throw new UsernameNotFoundException("User not found");
    }
}
