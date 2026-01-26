package com.example.Ecommerce.services;


import com.example.Ecommerce.exceptions.ProductNotFoundException;
import com.example.Ecommerce.models.CustomerModel;
import com.example.Ecommerce.models.FoodModel;
import com.example.Ecommerce.models.ReviewModel;
import com.example.Ecommerce.repositories.CustomerRepository;
import com.example.Ecommerce.repositories.FoodRepository;
import com.example.Ecommerce.repositories.ReviewRepository;
import com.example.Ecommerce.requests.CreateReviewRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final CustomerService customerService;
    private final FoodService foodService;

    public ReviewService(ReviewRepository reviewRepository, CustomerService customerService, FoodService foodService) {
        this.reviewRepository = reviewRepository;
        this.customerService = customerService;
        this.foodService = foodService;
    }

    public ReviewModel getReviewByFoodAndIdAndCustomer(Long foodId, Long customerId, Long id) {
        FoodModel foodModel= foodService.getFoodById(foodId);
        CustomerModel customerModel= customerService.findCustomerById(customerId);
        return reviewRepository.findByFoodAndIdAndCustomer(foodModel,id,customerModel).orElseThrow(()->new ProductNotFoundException("Review Not Found"));
    }

    public List<ReviewModel> getAllReviews() {
        return reviewRepository.findAll();
    }

    public ReviewModel createReview(CreateReviewRequest request) {
        ReviewModel reviewModel = ReviewModel.builder()
            .createdAt(LocalDateTime.now())
            .customer(request.getCustomer())
            .food(request.getFood())
            .comment(request.getComment())
            .rating(request.getRating())
            .build();
        return reviewRepository.save(reviewModel);
    }

    public ReviewModel updateReview(Long foodId, Long customerId, Long id,CreateReviewRequest request) {
        ReviewModel review= getReviewByFoodAndIdAndCustomer(foodId,customerId,id);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        return reviewRepository.save(review);
    }

    public void deleteReview(Long foodId, Long customerId, Long id) {
        ReviewModel review= getReviewByFoodAndIdAndCustomer(foodId,customerId,id);
        reviewRepository.delete(review);
    }
}
