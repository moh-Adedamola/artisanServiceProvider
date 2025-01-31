package com.semicolonafrica.dtos.response;

import com.semicolonafrica.data.model.Role;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class UserRoleResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Role role;


}
