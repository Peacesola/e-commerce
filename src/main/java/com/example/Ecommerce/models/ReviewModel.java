package com.example.Ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class ReviewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "food_id")
    private FoodModel food;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    private Integer rating;
    private String comment;

    private LocalDateTime createdAt = LocalDateTime.now();
}
