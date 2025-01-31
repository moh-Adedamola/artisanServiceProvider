package com.semicolonafrica.dtos.response;

import com.semicolonafrica.data.model.Review;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SendReviewResponse {
    private String message;
    private Review review;
}
