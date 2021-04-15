package com.bootcamp.finalProject.security;

import com.bootcamp.finalProject.model.User;
import com.bootcamp.finalProject.repositories.IUserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;



@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private IUserRepository userRep;

    /*
    @Autowired
    private PasswordEncoder bcryptEncoder;
    */

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRep.findByUsername(userName);
        //User user = new User("foo",bcryptEncoder.encode("foo"),true,new ArrayList<>());
        if(user == null){
            throw new Exception("invalid user");
        }
        return  org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
