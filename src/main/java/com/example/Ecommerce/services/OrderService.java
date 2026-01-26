package com.example.Ecommerce.services;

import com.example.Ecommerce.exceptions.ProductNotFoundException;
import com.example.Ecommerce.models.Cart;
import com.example.Ecommerce.models.CartItem;
import com.example.Ecommerce.models.OrderItem;
import com.example.Ecommerce.models.OrderModel;
import com.example.Ecommerce.repositories.CartRepository;
import com.example.Ecommerce.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    public OrderModel placeOrder(Long customerId){
        Cart cart=cartRepository.findById(customerId).orElseThrow(()->new ProductNotFoundException("Error fetching cart"));
        if(!(cart.getCartItems().isEmpty())){
            OrderModel orderModel= OrderModel.builder()
                .customer(cart.getCustomer())
                .createdAt(LocalDateTime.now())
                .build();

            Set<OrderItem> orderItems=new HashSet<>();

            for(CartItem cartItem: cart.getCartItems()){
                OrderItem orderItem= OrderItem.builder()
                    .order(orderModel)
                    .food(cartItem.getFood())
                    .price(cartItem.getFood().getPrice())
                    .build();

                orderItems.add(orderItem);
            }

            orderModel.setItems(orderItems);

            OrderModel savedOrder = orderRepository.save(orderModel);
            cart.getCartItems().clear();
            cartRepository.save(cart);

            return savedOrder;
        }
        else{
            throw new ProductNotFoundException("Product not found");
        }
    }
}
