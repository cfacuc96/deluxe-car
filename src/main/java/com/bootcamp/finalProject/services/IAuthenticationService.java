package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.AuthenticateDTO;

import javax.security.auth.login.LoginException;

public interface IAuthenticationService {

    String login (AuthenticateDTO auth) throws LoginException;
}
