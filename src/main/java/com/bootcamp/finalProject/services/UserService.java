package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.UserDTO;
import com.bootcamp.finalProject.model.Role;
import com.bootcamp.finalProject.model.User;
import com.bootcamp.finalProject.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class UserService implements IUserService{

    @Autowired
    private PasswordEncoder bcryptEncoder;

    IUserRepository userRepository;

    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;

    }

    @Override
    public void saveUser(UserDTO user) {
        User u = new User(user.getUsername(),bcryptEncoder.encode(user.getPassword()),true,null);
        userRepository.save(u);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void loadDefaultUsers() {

        User w = new User("warehouse",bcryptEncoder.encode("123"),true,new ArrayList<Role>(Arrays.asList(Role.ROLE_WAREHOUSE,Role.ROLE_SUBSIDIARY)));
        userRepository.save(w);

        User s = new User("subsidiary",bcryptEncoder.encode("123"),true,new ArrayList<Role>(Arrays.asList(Role.ROLE_SUBSIDIARY)));
        userRepository.save(s);
    }


}
