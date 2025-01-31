package com.semicolonafrica.dtos.request;

import com.semicolonafrica.data.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserRoleRequest {
    private Role role;
}
