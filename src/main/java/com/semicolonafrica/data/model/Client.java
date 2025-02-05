package com.semicolonafrica.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client extends User{

    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;

}
