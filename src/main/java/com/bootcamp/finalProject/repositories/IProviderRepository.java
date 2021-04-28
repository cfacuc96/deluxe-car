package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProviderRepository extends JpaRepository<Provider,Long> {


}
