package com.example.Ecommerce.services;


import com.example.Ecommerce.models.VendorModel;
import com.example.Ecommerce.requests.CreateFoodRequest;
import com.example.Ecommerce.requests.UpdateFoodRequest;
import com.example.Ecommerce.exceptions.ProductAlreadyExistsException;
import com.example.Ecommerce.exceptions.ProductNotFoundException;
import com.example.Ecommerce.models.FoodModel;
import com.example.Ecommerce.repositories.FoodRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final CloudinaryService cloudinaryService;
    public FoodService(FoodRepository foodRepository, CloudinaryService cloudinaryService) {
        this.foodRepository = foodRepository;
        this.cloudinaryService = cloudinaryService;
    }


    public List<FoodModel> getAllFoods() {
        return foodRepository.findAll();
    }

    public FoodModel getFoodById(Long id) {
        return foodRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found"));
    }

    public FoodModel getFoodByName(String name) {
        return foodRepository.findByName(name).orElseThrow(()->new ProductNotFoundException("Product not found"));
    }

    public List<FoodModel>searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    public void deleteFoodById(Long id) {
        FoodModel foodModel = foodRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found"));
        foodRepository.delete(foodModel);
    }

    public String uploadFoodImage (MultipartFile file) {
       var result= cloudinaryService.uploadFile(file);
       return result.get("secure_url").toString();
    }

    public FoodModel createFood(CreateFoodRequest createFoodRequest) {
        FoodModel foodModel = FoodModel.builder().price(createFoodRequest.getPrice())
            .name(createFoodRequest.getName())
            .isAvailable(createFoodRequest.getIsAvailable())
            .addOns(createFoodRequest.getAddOns())
            //.imageUrl(createFoodRequest.getImageUrl())
            .build();
        if(foodRepository.existsByName(createFoodRequest.getName())){
            throw new ProductAlreadyExistsException("Product already exists");
        }
         return foodRepository.save(foodModel);
    }



    public void updateFoodById(Long id, UpdateFoodRequest request) {
        FoodModel foodModel = getFoodById(id);
        foodModel.setName(request.getName());
        foodModel.setPrice(request.getPrice());
        foodModel.setIsAvailable(request.getIsAvailable());
        foodModel.setAddOns(request.getAddOns());
        foodRepository.save(foodModel);
    }

    public void toggleFoodAvailability(Long id, Boolean toggle){
        FoodModel food= getFoodById(id);
        food.setIsAvailable(toggle);
        foodRepository.save(food);
    }

}
