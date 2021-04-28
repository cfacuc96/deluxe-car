package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.DiscountRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRateRepository extends JpaRepository<DiscountRate, Long> {

}
