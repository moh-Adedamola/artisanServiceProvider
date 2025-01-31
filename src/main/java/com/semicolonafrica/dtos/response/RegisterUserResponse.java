package com.semicolonafrica.dtos.response;

import com.semicolonafrica.data.model.Role;
import com.semicolonafrica.data.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class RegisterUserResponse {
    private String message;
    private String firstName;
    private String lastName;
//    private String email;
    private Role role;
//    private LocalDateTime createdAt;
}
