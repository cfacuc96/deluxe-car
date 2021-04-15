package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.UserDTO;
import com.bootcamp.finalProject.model.Role;
import com.bootcamp.finalProject.model.Subsidiary;
import com.bootcamp.finalProject.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;

//@ExtendWith(SpringExtension.class)
//@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.datasource.driverClassName=org.h2.Driver"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SuppressWarnings("all")
@SpringBootTest
public class UserServiceTest {


    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    @BeforeEach
    public void loadUserNoErrorTest(){
        Assertions.assertDoesNotThrow(() -> userService.loadDefaultUsers());

    }

    @Test
    public void findByUsernameOk(){
        //Arrange
        String username = "uruSub";
        //Act & assert
        User u = userService.findByUsername(username);
        Assertions.assertEquals(username,u.getUsername());
    }

    @Test
    public void findByUserDetailsOk(){
        //Arrange
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("uruSub")
                .password("")
                .authorities(new ArrayList<>())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();


        //Act & assert
        User u = userService.findUserByUserDetails(userDetails);
        Assertions.assertEquals(userDetails.getUsername(),u.getUsername());
    }

    @Test
    public void findSubsidiaryByUserDetailsOk(){
        //Arrange
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("uruSub")
                .password("")
                .authorities(new ArrayList<>())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
        Subsidiary expected = new Subsidiary(null,"Montevideo subsidiary","Fake addres 123","7470-5556","Uruguay :)",null,null,null);

        //Act
        Subsidiary actual = userService.getSubsidiaryByUsername(userDetails);

        //Assert
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void saveUserOk(){
        //Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("pedrito");
        userDTO.setPassword("123");

        //Act & Assert

        Assertions.assertDoesNotThrow(()->userService.saveUser(userDTO));

        User u = userService.findByUsername("pedrito");

        Assertions.assertEquals(userDTO.getUsername(),u.getUsername());

    }



}
