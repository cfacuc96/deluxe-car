package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.dtos.UserDTO;
import com.bootcamp.finalProject.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    IUserService userService;
    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> newUser(@RequestBody UserDTO user){

        userService.saveUser(user);
        return ResponseEntity.ok("to gud");
    }

    @PostMapping("/load")
    public ResponseEntity<?> loadUsers(){
        userService.loadDefaultUsers();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/wareRol")
    @PreAuthorize("hasRole('ROLE_WAREHOUSE')")
    public ResponseEntity<?> testWareHouse(){
        return ResponseEntity.ok("nanana");
    }
}
