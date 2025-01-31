package com.semicolonafrica.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SendReviewRequest {
//    private Long senderId;
//    private Long receiverId;
    private Long jobId;
    private int rating;
    private String comment;


}
