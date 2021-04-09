package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.AuthenticateDTO;
import com.bootcamp.finalProject.dtos.UserDTO;
import com.bootcamp.finalProject.model.User;

public interface IUserService {

    void saveUser(UserDTO user);

    User findByUsername(String username);

    void loadDefaultUsers();
}
