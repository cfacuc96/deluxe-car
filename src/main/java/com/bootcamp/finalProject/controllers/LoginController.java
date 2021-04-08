package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.dtos.AutenticateDTO;
import com.bootcamp.finalProject.dtos.AutenticateResponseDTO;
import com.bootcamp.finalProject.dtos.UserDTO;
import com.bootcamp.finalProject.security.JwtUserDetailService;
import com.bootcamp.finalProject.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authManger;

    @Autowired
    private JwtUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> loginWithToken(@RequestBody UserDTO userDTO) throws Exception
    {


        try{
            authManger.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect Username or Password");
        }


        UserDetails userDetails = userDetailService.loadUserByUsername(userDTO.getUsername());
        String jwt  = jwtTokenUtil.generateToken(userDetails);

        AutenticateResponseDTO r = new AutenticateResponseDTO();
        r.setJwt(jwt);


        return ResponseEntity.ok(r);

    }

    @GetMapping("/getUsername")
    public String getUsername(){
        UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();


        return user.getUsername();
    }
}
