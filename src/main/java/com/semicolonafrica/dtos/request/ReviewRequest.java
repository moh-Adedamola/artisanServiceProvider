package com.semicolonafrica.dtos.request;

import com.semicolonafrica.data.model.Artisan;
import com.semicolonafrica.data.model.Category;
import com.semicolonafrica.data.model.Client;
import com.semicolonafrica.data.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class ReviewRequest {

    private long clientId;
    private long artisanId;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private int rating;

    @Size(max = 500, message = "Comment must be at most 500 characters")
    private String comment;

    private Category category;


}
