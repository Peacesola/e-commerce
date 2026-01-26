package com.example.Ecommerce.repositories;

import com.example.Ecommerce.models.Cart;
import com.example.Ecommerce.models.CartItem;
import com.example.Ecommerce.models.FoodModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByCartAndFood(Cart cart, FoodModel food);
}
