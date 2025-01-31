package com.semicolonafrica.controller;

import com.semicolonafrica.data.model.Review;
import com.semicolonafrica.dtos.request.RegisterUserRequest;
import com.semicolonafrica.dtos.request.ReviewRequest;
import com.semicolonafrica.dtos.request.SendReviewRequest;
import com.semicolonafrica.dtos.response.ApiResponse;
import com.semicolonafrica.dtos.response.RegisterUserResponse;
import com.semicolonafrica.dtos.response.ReviewResponse;
import com.semicolonafrica.dtos.response.SendReviewResponse;
import com.semicolonafrica.services.ReviewService;
import com.semicolonafrica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private final ReviewService reviewService;


    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @PostMapping("/sendReview")
    public ResponseEntity<?> createReview(@RequestBody ReviewRequest reviewRequest) {
        try{
            ReviewResponse reviewResponse = reviewService.createReview(reviewRequest);
            return new ResponseEntity<>(new ApiResponse(true,reviewResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

//    @GetMapping("/findReviewById/{id}")
//    public ResponseEntity<?> findById(@PathVariable("id") Long id){
//        try {
//            Review review = reviewService.findById(id);
//            return new ResponseEntity<>(new ApiResponse(true, review), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @DeleteMapping("/deleteReviewById/{id}")
//    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
//        try{
//            return new ResponseEntity<>(new ApiResponse(true, reviewService.deleteById(id)), HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }
}
