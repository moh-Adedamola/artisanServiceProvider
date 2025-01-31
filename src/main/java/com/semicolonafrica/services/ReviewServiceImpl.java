package com.semicolonafrica.services;

import com.semicolonafrica.data.model.Artisan;
import com.semicolonafrica.data.model.Client;
import com.semicolonafrica.data.model.Review;
import com.semicolonafrica.data.model.User;
import com.semicolonafrica.data.repository.ArtisanRepository;
import com.semicolonafrica.data.repository.ClientRepository;
import com.semicolonafrica.data.repository.ReviewRepository;
import com.semicolonafrica.data.repository.UserRepository;
import com.semicolonafrica.dtos.request.ReviewRequest;
import com.semicolonafrica.dtos.request.SendReviewRequest;
import com.semicolonafrica.dtos.response.DeleteReviewResponse;
import com.semicolonafrica.dtos.response.DeleteUserResponse;
import com.semicolonafrica.dtos.response.ReviewResponse;
import com.semicolonafrica.dtos.response.SendReviewResponse;
import com.semicolonafrica.exception.ReviewNotFoundException;
import com.semicolonafrica.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ArtisanRepository artisanRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ReviewResponse createReview(ReviewRequest reviewRequest) {
//        User client = clientRepository.findById(reviewRequest.getClientId())
//                .orElseThrow(() -> new UserNotFoundException("Client not found"));
//
//        User artisan = artisanRepository.findById(reviewRequest.getArtisanId())
//                .orElseThrow(() -> new UserNotFoundException("Artisan not found"));

        Review review = new Review();
        review.setClientId(reviewRequest.getClientId());
        review.setArtisanId(reviewRequest.getArtisanId());
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        review.setCreatedAt(LocalDateTime.now());

        reviewRepository.save(review);
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setMessage("Review created successfully");

        return reviewResponse;
    }
}
