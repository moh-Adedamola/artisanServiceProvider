package com.semicolonafrica.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String address;

    private String Location;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean isLoggedIn = false;

    @PrePersist
    private void setCreatedAt(){
        this.createdAt = now();
    }

    @PreUpdate
    private void setUpdatedAt(){
        this.updatedAt = now();
    }

}





