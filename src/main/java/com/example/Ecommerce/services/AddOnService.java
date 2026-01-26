package com.example.Ecommerce.services;

import com.example.Ecommerce.exceptions.ProductAlreadyExistsException;
import com.example.Ecommerce.exceptions.ProductNotFoundException;
import com.example.Ecommerce.models.AddOnModel;
import com.example.Ecommerce.models.FoodModel;
import com.example.Ecommerce.repositories.AddOnRepository;
import com.example.Ecommerce.repositories.FoodRepository;
import com.example.Ecommerce.requests.AddOnRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddOnService {
    private final AddOnRepository addOnRepository;
    private final FoodService foodService;
    public AddOnService(AddOnRepository addOnRepository, FoodService foodService) {
        this.addOnRepository = addOnRepository;
        this.foodService = foodService;
    }

    public AddOnModel getAddOnById(Long id) {
        return addOnRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found"));
    }

    public void includeAddOn(Long foodId, AddOnRequest addOnRequest) {
       FoodModel foodModel= foodService.getFoodById(foodId);
        AddOnModel addOnModel= AddOnModel.builder()
            .name(addOnRequest.getName())
            .price(addOnRequest.getPrice())
            .food(foodModel)
            .build();
        if(addOnRepository.existsByName(addOnRequest.getName())){
            throw new ProductAlreadyExistsException("Add-on already exists");
        }
         addOnRepository.save(addOnModel);
    }

    public void removeAddOn(Long foodId, Long addOnId) {
        FoodModel foodModel= foodService.getFoodById(foodId);
       AddOnModel addOnModel=addOnRepository.findByFoodAndId(foodModel,addOnId)
           .orElseThrow(()->new ProductNotFoundException("Add-on does not exist"));
        addOnRepository.delete(addOnModel);
    }

}
