package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.UserDTO;
import com.bootcamp.finalProject.model.Role;
import com.bootcamp.finalProject.model.Subsidiary;
import com.bootcamp.finalProject.model.User;
import com.bootcamp.finalProject.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

       User u1 = new User("warehouse",bcryptEncoder.encode("123"),true,new ArrayList<Role>(Arrays.asList(Role.ROLE_WAREHOUSE,Role.ROLE_SUBSIDIARY)));
       userRepository.save(u1);


        User u2 = new User("uruSub",bcryptEncoder.encode("123"),true,new ArrayList<Role>(Arrays.asList(Role.ROLE_SUBSIDIARY)));
        List<User> u = new ArrayList<>();
        Subsidiary sub = new Subsidiary(null,"Montevideo subsidiary","Fake addres 123","7470-5556","Uruguay :)",null,null,u);
        u2.setSubsidiary(sub);

        userRepository.save(u2);

        User u3 = new User("argSub",bcryptEncoder.encode("123"),true,new ArrayList<Role>(Arrays.asList(Role.ROLE_SUBSIDIARY)));
        u = new ArrayList<>();
        sub = new Subsidiary(null,"Buenos Aires subsidiary","Fake addres 123","7470-5556","Argentina :)",null,null,u);
        u3.setSubsidiary(sub);

        userRepository.save(u3);

        User u4 = new User("colSub",bcryptEncoder.encode("123"),true,new ArrayList<Role>(Arrays.asList(Role.ROLE_SUBSIDIARY)));
        u = new ArrayList<>();
        sub = new Subsidiary(null,"Bogota subsidiary","Fake addres 123","7470-5556","Bogota :)",null,null,u);
        u4.setSubsidiary(sub);
        userRepository.save(u4);

    }


}
