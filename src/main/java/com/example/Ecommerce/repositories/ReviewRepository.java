package com.example.Ecommerce.repositories;

import com.example.Ecommerce.models.CustomerModel;
import com.example.Ecommerce.models.FoodModel;
import com.example.Ecommerce.models.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<ReviewModel,Long> {
    Optional<ReviewModel> findByFoodAndIdAndCustomer(FoodModel foodModel, Long id, CustomerModel customerModel);
}
