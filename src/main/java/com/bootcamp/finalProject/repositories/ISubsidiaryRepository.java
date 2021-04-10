package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.Subsidiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ISubsidiaryRepository extends JpaRepository<Subsidiary, Long> {
}
