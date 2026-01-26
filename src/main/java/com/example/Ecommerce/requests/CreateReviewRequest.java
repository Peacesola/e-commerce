package com.example.Ecommerce.requests;


import com.example.Ecommerce.models.CustomerModel;
import com.example.Ecommerce.models.FoodModel;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class CreateReviewRequest {
    private Integer rating;
    private String comment;
    private FoodModel food;
    private CustomerModel customer;
}
