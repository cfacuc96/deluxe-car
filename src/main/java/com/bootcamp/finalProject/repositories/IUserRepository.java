package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
