package com.example.Ecommerce.dtos;

import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AddOnDto {
    private Long id;
    private String name;
    private BigDecimal price;
}
