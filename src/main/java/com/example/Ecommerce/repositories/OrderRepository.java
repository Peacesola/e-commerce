package com.example.Ecommerce.repositories;

import com.example.Ecommerce.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
