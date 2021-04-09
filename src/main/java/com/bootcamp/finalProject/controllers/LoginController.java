package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.dtos.AuthenticateDTO;
import com.bootcamp.finalProject.dtos.AutenticateResponseDTO;
import com.bootcamp.finalProject.dtos.UserDTO;
import com.bootcamp.finalProject.security.JwtUserDetailService;
import com.bootcamp.finalProject.services.IAuthenticationService;
import com.bootcamp.finalProject.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authManger;

    @Autowired
    private JwtUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private IAuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticateDTO authenticate) throws LoginException {


        return ResponseEntity.ok(authService.login(authenticate));
    }


    @GetMapping("/getUsername")
    public String getUsername(){
        UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();


        return user.getUsername();
    }
}
