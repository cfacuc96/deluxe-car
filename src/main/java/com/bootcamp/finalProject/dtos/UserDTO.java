package com.bootcamp.finalProject.dtos;

import com.bootcamp.finalProject.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String password;
    private List<Role> roles;
}
