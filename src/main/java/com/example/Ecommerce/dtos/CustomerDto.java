package com.example.Ecommerce.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDto {
    private String token;
    private Long id;
    private String email;
}
