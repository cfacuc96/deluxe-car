package com.bootcamp.finalProject.services;


import com.bootcamp.finalProject.dtos.AuthenticateDTO;
import com.bootcamp.finalProject.exceptions.LoginInvalidException;

public interface IAuthenticationService {

    String login (AuthenticateDTO auth) throws LoginInvalidException;
}
