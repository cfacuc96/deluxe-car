package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {
    @Query("from User u  where u.username = :username")
    User findByUsername(@Param("username") String username);

    @Query("from User u join  u.subsidiary s where u.username = :username")
    User findByUsernameWithSubsidiary(@Param("username") String username);
}
