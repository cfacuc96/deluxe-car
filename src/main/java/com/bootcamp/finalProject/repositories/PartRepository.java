package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    //SELECT * FROM parts p
    //INNER JOIN part_records pr ON p.id_part = pr.id_part
    //WHERE p.last_modification >= :date
    //ORDER BY pr.created_at DESC
    @Query("FROM Part p WHERE p.lastModification >= :date")
    List<Part> findParcialPartByLastModification(@Param("date") Date date);

    //SELECT * FROM parts p
    //INNER JOIN part_records pr ON p.id_part = pr.id_part
    //WHERE pr.created_at >= :date
    //ORDER BY pr.created_at DESC
//    @Query("FROM Movie m WHERE m.title LIKE %:title%")
//    List<Part> findVariationPartByPriceCreateAt(@Param("date") Date date);
}
