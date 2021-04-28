package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.model.PartRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IPartRecordRepository extends JpaRepository<PartRecord, Long>, JpaSpecificationExecutor<PartRecord> {
}
