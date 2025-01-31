package com.semicolonafrica.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client extends User{

    @Size(max = 200, message = "Address must be at most 200 characters")
    private String address;

}
