package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.model.Subsidiary;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ISubsidiaryRepository extends JpaRepository<Subsidiary, Long> {

    @Query("FROM Subsidiary s WHERE s.id >= :date")
    Subsidiary findByDeliveryStatus(@Param("idSubsidiary") Long idSubsidiary, @Param("deliveryStatus") String deliveryStatus);
}
