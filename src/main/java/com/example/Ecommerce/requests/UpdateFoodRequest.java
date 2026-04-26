package com.example.Ecommerce.requests;


import com.example.Ecommerce.models.AddOnModel;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpdateFoodRequest {
    private BigDecimal price;
    private String name;
    private Boolean isAvailable;
    private Map<String,Integer> addOns= new HashMap<>();
    private String description;
}
