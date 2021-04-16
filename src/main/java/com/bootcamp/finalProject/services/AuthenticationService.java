package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.AuthenticateDTO;
import com.bootcamp.finalProject.exceptions.LoginInvalidException;
import com.bootcamp.finalProject.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.security.auth.login.LoginException;

@Service
public class AuthenticationService implements IAuthenticationService{
    @Autowired
    IUserService userService;


    @Autowired
    private JwtUtil jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String login(AuthenticateDTO auth) throws LoginInvalidException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(),auth.getPassword()));
            return jwtTokenProvider.createToken(auth.getUsername(), userService.findByUsername(auth.getUsername()).getRoles());
        } catch (Exception e) {
            throw new LoginInvalidException();
        }
    }
}
