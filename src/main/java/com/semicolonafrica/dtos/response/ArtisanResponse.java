package com.semicolonafrica.dtos.response;

import com.semicolonafrica.data.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ArtisanResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Category category;
    private String address;




}


