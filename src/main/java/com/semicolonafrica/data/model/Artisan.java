package com.semicolonafrica.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "artisans")
public class Artisan extends User{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @Size(max = 200, message = "Address must be at most 200 characters")
    private String address;

}
