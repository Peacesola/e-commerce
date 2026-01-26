package com.example.Ecommerce.requests;


import com.example.Ecommerce.models.AddOnModel;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpdateFoodRequest {
    private BigDecimal price;
    private String name;
    private Boolean isAvailable;
    private List<AddOnModel> addOns;
}
