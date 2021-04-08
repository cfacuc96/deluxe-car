package com.bootcamp.finalProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;
    private boolean active;
    private String rol;

    public User(String username, String password, boolean active, String rol) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.rol = rol;
    }
}
