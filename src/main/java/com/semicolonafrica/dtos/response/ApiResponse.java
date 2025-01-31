package com.semicolonafrica.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
    boolean isSuccessful;
    Object UserResponse;
}
