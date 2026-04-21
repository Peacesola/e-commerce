package com.example.Ecommerce.requests;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class CreateFoodRequest {
    @NotNull(message = "Price is required")
    private BigDecimal price;
    @NotNull(message = "Name is required")
    private String name;
    private String description;
    private Boolean isAvailable;
    private Map<String,Integer> addOns= new HashMap<>();
    //private String imageUrl;
}
