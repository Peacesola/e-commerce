package com.example.Ecommerce.repositories;

import com.example.Ecommerce.models.CustomerModel;
import com.example.Ecommerce.models.FoodModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
    Optional<CustomerModel> findByEmail(String email);
    Boolean  existsByEmail(String email);
}
