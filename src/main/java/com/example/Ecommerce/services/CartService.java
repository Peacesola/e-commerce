package com.example.Ecommerce.services;

import com.example.Ecommerce.exceptions.ProductNotFoundException;
import com.example.Ecommerce.models.Cart;
import com.example.Ecommerce.models.CartItem;
import com.example.Ecommerce.models.CustomerModel;
import com.example.Ecommerce.models.FoodModel;
import com.example.Ecommerce.repositories.CartItemRepository;
import com.example.Ecommerce.repositories.CartRepository;
import com.example.Ecommerce.repositories.CustomerRepository;
import com.example.Ecommerce.repositories.FoodRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final FoodService foodService;
   private final CartItemRepository cartItemRepository;
   private final CustomerRepository customerRepository;
    public CartService(CartRepository cartRepository, FoodService foodService, CartItemRepository cartItemRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.foodService = foodService;
        this.cartItemRepository = cartItemRepository;
        this.customerRepository = customerRepository;
    }


    public Cart addToCart(Long customerId, Long foodId, Integer quantity ){
       CustomerModel customerModel= customerRepository.findById(customerId).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        Cart cart = cartRepository.findById(customerId).
            orElseGet(() -> Cart.builder()
                .customer(customerModel)
                .build()
        );
        cartRepository.save(cart);

        FoodModel food= foodService.getFoodById(foodId);

        Optional<CartItem>existingCartItem=cartItemRepository.findByCartAndFood(cart,food);
        if(existingCartItem.isPresent()){
            existingCartItem.get().setQuantity(existingCartItem.get().getQuantity()+quantity);
            cartItemRepository.save(existingCartItem.get());
        }else{
            CartItem cartItem= CartItem.builder()
                .cart(cart)
                .food(food)
                .quantity(quantity).build();
            cart.getCartItems().add(cartItem);
            cartItemRepository.save(cartItem);
        }
        return cart;
    }

    public Cart getCartById(Long cartId){
        return cartRepository.findById(cartId).orElseThrow(()-> new ProductNotFoundException("Product not found"));
    }

    public void clearCart(Long cartId){
        Cart cart= getCartById(cartId);
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

    public void removeFromCart(Long customerId, Long foodId){
        CustomerModel customerModel= customerRepository.findById(customerId).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        Cart cart = cartRepository.findById(customerId).
            orElseGet(() -> Cart.builder()
                .customer(customerModel)
                .build()
            );
        cartRepository.save(cart);

        FoodModel food= foodService.getFoodById(foodId);
        Optional<CartItem>existingCartItem=cartItemRepository.findByCartAndFood(cart,food);
        if(existingCartItem.isPresent()){
            cartItemRepository.delete(existingCartItem.get());
        }else {
            throw new ProductNotFoundException("Failed to remove item");
        }
    }

}
