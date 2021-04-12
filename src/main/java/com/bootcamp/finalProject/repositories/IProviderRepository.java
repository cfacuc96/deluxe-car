package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.Provider;
import com.bootcamp.finalProject.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProviderRepository extends JpaRepository<Provider,Long> {


}
