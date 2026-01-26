package com.example.Ecommerce.responses;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponse {
    private Long id;
    private String email;
}
