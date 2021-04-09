package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.model.Subsidiary;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ISubsidiaryRepository extends JpaRepository<Subsidiary, Long> {

    @Query("FROM Subsidiary s JOIN s.orders o WHERE s.idSubsidiary = :idSubsidiary AND o.deliveryStatus = :deliveryStatus ORDER BY o.orderDate ASC")
    Subsidiary findByDeliveryStatus(@Param("idSubsidiary") Long idSubsidiary, @Param("deliveryStatus") String deliveryStatus);

    @Query("FROM Subsidiary s WHERE s.idSubsidiary = :idSubsidiary")
    Subsidiary findByIdOrder(@Param("idSubsidiary") Long idSubsidiary);
}
