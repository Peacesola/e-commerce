package com.example.Ecommerce.responses;


import com.example.Ecommerce.models.AddOnModel;
import com.example.Ecommerce.models.ReviewModel;
import com.example.Ecommerce.requests.AddOnRequest;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FoodResponse {
    private Long productId;
    private BigDecimal price;
    private String name;
    private Boolean isAvailable;
    private String description;
   // private List<ReviewModel> reviews;
   // private List<AddOnRequest> addOns= new ArrayList<>();
   private List<Map<String,Object>> addOns= new ArrayList<>();
}
