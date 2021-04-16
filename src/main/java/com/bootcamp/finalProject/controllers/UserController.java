package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.dtos.UserDTO;
import com.bootcamp.finalProject.exceptions.UsernameInUseException;
import com.bootcamp.finalProject.services.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@Api(value = "User Controller - This controller allow to manipulate Users")
public class UserController  extends CentralController{

    IUserService userService;
    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }
    @ApiOperation(value = "Allows you to register a new user",nickname = "Register new User")
    @PostMapping("/register")
    public ResponseEntity<?> newUser(@Valid @RequestBody UserDTO user){

        userService.saveUser(user);
        return ResponseEntity.ok("User crated Successfully");
    }

    @PostMapping("/load")
    public ResponseEntity<?> loadUsers() throws UsernameInUseException {
        userService.loadDefaultUsers();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/wareRol")
    @PreAuthorize("hasRole('ROLE_WAREHOUSE')")
    public ResponseEntity<?> testWareHouse(){
        return ResponseEntity.ok("nanana");
    }
}
