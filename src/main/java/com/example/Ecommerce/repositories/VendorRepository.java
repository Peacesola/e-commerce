package com.example.Ecommerce.repositories;

import com.example.Ecommerce.models.VendorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<VendorModel,Long> {
    Optional<VendorModel> findByEmail(String email);
    Optional<VendorModel> findByRestaurantName(String restaurantName);
    Boolean existsByRestaurantName(String restaurantName);
    Boolean existsByEmail(String email);
}
