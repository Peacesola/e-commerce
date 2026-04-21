package com.example.Ecommerce.responses;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddOnResponse {
    private Long id;
    private String name;
    private BigDecimal price;
}
