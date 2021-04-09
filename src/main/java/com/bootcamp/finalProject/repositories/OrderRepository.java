package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("FROM Order o WHERE o.deliveryStatus = :deliveryStatus")
    List<Order> findByDeliveryStatus(@Param("deliveryStatus") String deliveryStatus, Sort sort);

}
