package com.example.Ecommerce.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VendorDto {
    private Long id;
    private String restaurantName;
    private String email;
    private Boolean isOpen;
    private String token;
}
