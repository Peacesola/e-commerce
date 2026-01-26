package com.example.Ecommerce.responses;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VendorResponse {
    private Long id;
    private String restaurantName;
    private String email;
    private Boolean isOpen;
}
