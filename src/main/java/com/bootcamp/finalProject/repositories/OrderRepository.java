package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.Order;
import com.bootcamp.finalProject.model.Subsidiary;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * find all the orders related to the id of a subsidiary
     * @param idSubsidiary
     * @param sort
     * @return List<Order> related to the id of a subsidiary
     */
    @Query("FROM Order o WHERE o.subsidiary.idSubsidiary = :idSubsidiary")
    List<Order> findByIdSubsidiary(@Param("idSubsidiary") Long idSubsidiary, Sort sort);

    /**
     * find all orders related to a subsidiary id and a delivery status type
     * @param idSubsidiary
     * @param deliveryStatus
     * @param sort
     * @return List<Order> related to a subsidiary id and a delivery status type
     */
    @Query("FROM Order o WHERE o.subsidiary.idSubsidiary = :idSubsidiary AND o.deliveryStatus = :deliveryStatus")
    List<Order> findByIdSubsidiaryAndDeliveryStatus(@Param("idSubsidiary") Long idSubsidiary, @Param("deliveryStatus") String deliveryStatus, Sort sort);


    Optional<Order> findByIdOrderAndSubsidiary(@Param("id")Long id,@Param("subsidiary") Subsidiary subsidiary);
}
