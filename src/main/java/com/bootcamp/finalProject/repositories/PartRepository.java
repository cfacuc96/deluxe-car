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

    /**
     * @param date Not Null
     * @param sort ASC BY partCode, 1 ASC BY description, 2 DESC BY description, 3 DESC by lastModification
     * @return Finds all parts that have been modified since the date
     */
    @Query("FROM Part p WHERE p.lastModification >= :date")
    List<Part> findByLastModification(@Param("date") Date date, Sort sort);

    /**
     * @param date Not Null
     * @param sort ASC BY partCode, 1 ASC BY description, 2 DESC BY description, 3 DESC by lastModification
     * @return Finds all the parts that the price was modified from the date
     */
    @Query("FROM Part p JOIN p.partRecords pr WHERE pr.createdAt >= :date")
    List<Part> findByPriceCreateAt(@Param("date") Date date, Sort sort);
}
