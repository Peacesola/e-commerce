package com.example.Ecommerce.controllers;

import com.example.Ecommerce.models.FoodModel;
import com.example.Ecommerce.models.ReviewModel;
import com.example.Ecommerce.requests.CreateFoodRequest;
import com.example.Ecommerce.requests.CreateReviewRequest;
import com.example.Ecommerce.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/createReview")
    public ResponseEntity<Map<String,Object>> createReview(@RequestBody CreateReviewRequest request) {
      ReviewModel reviewModel= reviewService.createReview(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            Map.of(
                "message", "Review submitted successfully",
                "status code", HttpStatus.CREATED.value(),
                "review", reviewModel
            ));
    }

    @GetMapping("/getAllReviews")
    public ResponseEntity<Map<String,Object>>getAllReviews(){
        List<ReviewModel> allReviews= reviewService.getAllReviews();
        return ResponseEntity.status(HttpStatus.OK).body(
            Map.of(
                "message", "Reviews loaded successfully",
                "status code", HttpStatus.OK.value(),
                "reviews", allReviews
            ));
    }

    @PutMapping("/editReview/{foodId}/{customerId}/{id}")
    public ResponseEntity<Map<String,Object>> updateReview(@RequestBody CreateReviewRequest request,
   @PathVariable Long foodId ,@PathVariable Long customerId, @PathVariable Long id) {
        ReviewModel reviewModel= reviewService.updateReview(foodId,customerId,id,request);
        return ResponseEntity.status(HttpStatus.OK).body(
            Map.of(
                "message", "Review edited successfully",
                "status code", HttpStatus.OK.value()
            ));
    }

    @DeleteMapping("/deleteReview/{foodId}/{customerId}/{id}")
    public ResponseEntity<Map<String,Object>> deleteReview(@PathVariable Long foodId
        ,@PathVariable Long customerId, @PathVariable Long id){
        reviewService.deleteReview(foodId,customerId,id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            Map.of(
                "message", "Review deleted successfully",
                "status code", HttpStatus.NO_CONTENT.value()
            ));
    }

}
