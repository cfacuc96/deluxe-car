package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;


import java.util.Date;
import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    @Query("FROM Part p WHERE p.lastModification >= :date")
    List<Part> findByLastModification(@Param("date") Date date, Sort sort);

    @Query("FROM Part p JOIN p.partRecords pr WHERE pr.createdAt >= :date")
    List<Part> findByPriceCreateAt(@Param("date") Date date, Sort sort);
}
