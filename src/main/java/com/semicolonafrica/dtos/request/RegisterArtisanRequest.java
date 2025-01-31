package com.semicolonafrica.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterArtisanRequest {
    @NotNull(message = "User ID is required")
    private Long id;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Bio is required")
    private String description;
}
