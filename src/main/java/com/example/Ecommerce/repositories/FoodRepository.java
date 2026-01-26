package com.example.Ecommerce.repositories;

import com.example.Ecommerce.models.FoodModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<FoodModel, Long> {
    Optional<FoodModel> findByName(String name);
    boolean existsByName(String name);
    @Query("SELECT f from FoodModel f WHERE" +
        " LOWER(f.name) LIKE LOWER(CONCAT('%', :keyword, '%') ) ")
    List<FoodModel> searchFood(String keyword);
}
