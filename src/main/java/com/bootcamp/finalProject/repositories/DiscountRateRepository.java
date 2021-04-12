package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.DiscountRate;
import com.bootcamp.finalProject.model.Order;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRateRepository extends JpaRepository<DiscountRate, Long> {

}
