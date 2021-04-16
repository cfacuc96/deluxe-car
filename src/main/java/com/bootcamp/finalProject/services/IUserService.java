package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.AuthenticateDTO;
import com.bootcamp.finalProject.dtos.UserDTO;
import com.bootcamp.finalProject.exceptions.UsernameInUseException;
import com.bootcamp.finalProject.model.Subsidiary;
import com.bootcamp.finalProject.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    void saveUser(UserDTO user);

    User findByUsername(String username);

    User findUserByUserDetails(UserDetails userDetails);

    Subsidiary  getSubsidiaryByUsername(UserDetails userDetails);
    void loadDefaultUsers() throws UsernameInUseException, UsernameInUseException;
}
