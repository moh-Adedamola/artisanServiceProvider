package com.semicolonafrica.dtos.request;

import com.semicolonafrica.data.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FindArtisanByCategoryRequest {
    private String category;
}
