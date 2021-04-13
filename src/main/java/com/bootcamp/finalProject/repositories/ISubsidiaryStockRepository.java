package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.SubsidiaryStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISubsidiaryStockRepository extends JpaRepository<SubsidiaryStock, Long> {

    @Query("FROM SubsidiaryStock s WHERE s.subsidiary = :idSubsidiary")
    List<SubsidiaryStock> findByLastModification(@Param("idSubsidiary") Integer idSubsidiary);
}
