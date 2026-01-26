package com.example.Ecommerce.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class FoodModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private String name;
    private Boolean isAvailable;
    //private String imageUrl;
    @OneToMany(
        mappedBy = "food"
    )
    @JsonManagedReference
    private List<ReviewModel> reviews;
    @OneToMany(
        mappedBy = "food",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonManagedReference
    private List<AddOnModel> addOns= new ArrayList<>();
}
