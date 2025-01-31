package com.semicolonafrica.dtos.request;

import com.semicolonafrica.data.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UpdateUserProfileRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
}
