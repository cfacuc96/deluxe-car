package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.dtos.AuthenticateDTO;
import com.bootcamp.finalProject.exceptions.LoginInvalidException;
import com.bootcamp.finalProject.services.IAuthenticationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends CentralController{

    @Autowired
    private IAuthenticationService authService;

    @ApiOperation(
            value = "Allows you to login in the system, giving to you a JWT token",
            nickname = "Login")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticateDTO authenticate) throws LoginInvalidException {

        return ResponseEntity.ok(authService.login(authenticate));
    }

    @GetMapping("/getUsername")
    public String getUsername(){
        UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        return user.getUsername();
    }

}
