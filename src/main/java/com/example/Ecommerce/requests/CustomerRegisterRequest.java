package com.example.Ecommerce.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegisterRequest {
    @NotNull(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;

    @NotNull(message = "Password is required")
    private String password;
}
