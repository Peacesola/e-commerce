package com.example.Ecommerce.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class AddOnRequest {
    //@NotNull(message = "Price is required")
    private BigDecimal price;
    //@NotNull(message = "Name is required")
    private String name;
}
