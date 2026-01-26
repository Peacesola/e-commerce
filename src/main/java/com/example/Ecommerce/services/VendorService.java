package com.example.Ecommerce.services;

import com.example.Ecommerce.dtos.CustomerDto;
import com.example.Ecommerce.dtos.VendorDto;
import com.example.Ecommerce.exceptions.ProductNotFoundException;
import com.example.Ecommerce.exceptions.UserAlreadyExistsException;
import com.example.Ecommerce.models.CustomerModel;
import com.example.Ecommerce.models.VendorModel;
import com.example.Ecommerce.repositories.CustomerRepository;
import com.example.Ecommerce.repositories.VendorRepository;
import com.example.Ecommerce.requests.CustomerLoginRequest;
import com.example.Ecommerce.requests.CustomerRegisterRequest;
import com.example.Ecommerce.requests.VendorLoginRequest;
import com.example.Ecommerce.requests.VendorRegisterRequest;
import com.example.Ecommerce.responses.VendorResponse;
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
public class VendorService {
    private final VendorRepository vendorRepository;
    private final CustomerRepository customerRepository;
    private final AuthenticationManager manager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    public VendorService(VendorRepository vendorRepository, CustomerRepository customerRepository, AuthenticationManager manager, JwtService jwtService, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.vendorRepository = vendorRepository;
        this.customerRepository = customerRepository;
        this.manager = manager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    public VendorResponse registerRestaurant(VendorRegisterRequest request){
        VendorModel vendorModel =  VendorModel.builder().
            restaurantName(request.getRestaurantName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .isOpen(request.getIsOpen())
            .build();
        if(vendorRepository.existsByEmail(request.getEmail())||
            customerRepository.existsByEmail(request.getEmail())
        ){
            throw new UserAlreadyExistsException("There is already an account with that email");
        }
       /* if(vendorRepository.existsByRestaurantName(request.getRestaurantName())){
            throw new UserAlreadyExistsException("There is already a restaurant with that name");
        }*/
        VendorModel saved = vendorRepository.save(vendorModel);
        return VendorResponse.builder().id(saved.getRestaurantId())
            .email(saved.getEmail())
            .restaurantName(saved.getRestaurantName())
            .isOpen(saved.getIsOpen())
            .build();
    }

    public VendorDto loginVendor(VendorLoginRequest request){
        manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails userDetails= userDetailsService.loadUserByUsername(request.getEmail());
        VendorModel model=vendorRepository.findByEmail(request.getEmail()).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        String token= jwtService.generateToken(model.getEmail());

        return VendorDto.builder()
            .token(token)
            .email(userDetails.getUsername())
            .id(model.getRestaurantId())
            .build();
    }

    public VendorDto loggedInUser() {
        Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        VendorModel model= vendorRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return VendorDto.builder()
            .email(email)
            .id(model.getRestaurantId())
            .build();

    }

    public VendorModel getRestaurantById(Long id){
        return vendorRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Restaurant not found"));
    }

    public List<VendorModel> getAllRestaurants(){
        return vendorRepository.findAll();
    }

    public void toggleRestaurantState(Long id, Boolean toggle){
       VendorModel restaurant= getRestaurantById(id);
        restaurant.setIsOpen(toggle);
       vendorRepository.save(restaurant);
    }
}
