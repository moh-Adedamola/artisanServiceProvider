package com.semicolonafrica.dtos.response;

import com.semicolonafrica.data.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String message;
    private String firstName;
    private String lastName;
    private Role role;

}
