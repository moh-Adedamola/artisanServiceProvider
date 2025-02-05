package com.semicolonafrica.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admins")
public class Admin extends User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;


}
