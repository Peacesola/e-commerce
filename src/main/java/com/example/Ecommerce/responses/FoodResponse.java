package com.example.Ecommerce.responses;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
   private Map<String,Integer> addOns= new HashMap<>();
}
