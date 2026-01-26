package com.example.Ecommerce.repositories;

import com.example.Ecommerce.models.AddOnModel;
import com.example.Ecommerce.models.FoodModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddOnRepository extends JpaRepository<AddOnModel,Long> {
    Optional<AddOnModel> findByName(String name);
    boolean existsByName(String name);
    Optional<AddOnModel>findByFoodAndId(FoodModel food, Long id);
}
