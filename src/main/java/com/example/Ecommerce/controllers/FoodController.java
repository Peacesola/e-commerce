package com.example.Ecommerce.controllers;

import com.example.Ecommerce.models.CustomerModel;
import com.example.Ecommerce.models.FoodModel;
import com.example.Ecommerce.requests.AddOnRequest;
import com.example.Ecommerce.requests.CreateFoodRequest;
import com.example.Ecommerce.requests.UpdateFoodRequest;
import com.example.Ecommerce.services.AddOnService;
import com.example.Ecommerce.services.CloudinaryService;
import com.example.Ecommerce.services.FoodService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/food")
public class FoodController {

    private final FoodService foodService;
    private final AddOnService addOnService;
    private final CloudinaryService cloudinaryService;

    public FoodController(FoodService foodService, AddOnService addOnService, CloudinaryService cloudinaryService) {
        this.foodService = foodService;
        this.addOnService = addOnService;
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/createFood")
    public ResponseEntity<Map<String,Object>> createFood(@RequestBody @Valid CreateFoodRequest createFoodRequest) {
        FoodModel foodModel= foodService.createFood(createFoodRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            Map.of(
                "message", "Food created successfully",
                "status code", HttpStatus.CREATED.value(),
                "food", foodModel
            ));
    }

    @GetMapping("/getAllFoodItems")
    public List<FoodModel> getAllFoodItems() {
        return foodService.getAllFoods();
    }


    @PostMapping("/foodAvailability/{id}/{toggle}")
    public ResponseEntity<Map<String,Object>> toggleFoodAvailability(@PathVariable Long id,@PathVariable Boolean toggle){
        foodService.toggleFoodAvailability(id,toggle);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "message","Availability updated successfully!",
            "status code",HttpStatus.OK.value(),
            "state",toggle
        ));
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Map<String,Object>> getFoodById(@PathVariable Long id){
        FoodModel food= foodService.getFoodById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "status code",HttpStatus.OK.value(),
            "food",food
        ));
    }

    @GetMapping("getByName/{name}")
    ResponseEntity<Map<String,Object>>getFoodByName(@PathVariable String name){
        FoodModel food= foodService.getFoodByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "status code",HttpStatus.OK.value(),
            "food",food
        ));
    }

    @DeleteMapping("deleteFood/{id}")
    public ResponseEntity<Map<String,Object>> deleteFoodById(@RequestParam Long id){
        foodService.deleteFoodById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of(
            "status code",HttpStatus.NO_CONTENT.value(),
            "message","Food deleted successfully!"
        ));
    }

    @PutMapping("updateFood/{id}")
    public ResponseEntity<Map<String,Object>> updateFood(@RequestBody UpdateFoodRequest updateFoodRequest
        ,@PathVariable Long id){
        foodService.updateFoodById(id,updateFoodRequest);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "status code",HttpStatus.OK.value(),
            "message","Food updated successfully!"
        ));
    }

    @PostMapping("/{foodId}/addOns")
    public ResponseEntity<Map<String,Object>>includeAddOn(@PathVariable Long foodId, @RequestBody AddOnRequest addOnRequest){
        addOnService.includeAddOn(foodId,addOnRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
            "message","AddOn added successfully!",
            "status code",HttpStatus.CREATED.value()
        ));
    }

    @GetMapping("/searchFood")
    public ResponseEntity<List<FoodModel>> searchFood(@RequestParam String keyword){
        List<FoodModel> food= foodService.searchFood(keyword);
        return new ResponseEntity<>(food,HttpStatus.OK);
    }

    @PostMapping("{id}/uploadImage")
    public ResponseEntity<Map<String,Object>>uploadFoodImage(@RequestParam("file") MultipartFile file,@PathVariable Long id){
        Map result = cloudinaryService.uploadFile(file);
        String url= result.get("secure_url").toString();
        String publicId= result.get("public_id").toString();
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
            "url", url,
            "public_id", publicId
        ));
    }

}
