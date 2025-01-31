package com.semicolonafrica.services;

import com.semicolonafrica.data.model.Review;
import com.semicolonafrica.dtos.request.ReviewRequest;
import com.semicolonafrica.dtos.request.SendReviewRequest;
import com.semicolonafrica.dtos.response.DeleteReviewResponse;
import com.semicolonafrica.dtos.response.ReviewResponse;
import com.semicolonafrica.dtos.response.SendReviewResponse;

import java.util.List;

public interface ReviewService {
    ReviewResponse createReview(ReviewRequest reviewRequest);
}
