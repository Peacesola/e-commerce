package com.example.Ecommerce.requests;

import com.example.Ecommerce.models.AddOnModel;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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
    private Boolean isAvailable;
    private List<AddOnModel> addOns;
    private String imageUrl;
}
