package com.semicolonafrica.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.semicolonafrica.data.model.Category;
import com.semicolonafrica.data.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {

    private String firstName;
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    private String password;
    private String phoneNumber;
    private Role role;
    private String address;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String description;
    private String location;





}
