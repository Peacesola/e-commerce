package com.example.Ecommerce.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorRegisterRequest {
    @NotNull(message = "Restaurant name is required")
    private String restaurantName;
    @NotNull(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;
    @NotNull(message = "Password is required")
    private String password;
    private Boolean isOpen;
}
