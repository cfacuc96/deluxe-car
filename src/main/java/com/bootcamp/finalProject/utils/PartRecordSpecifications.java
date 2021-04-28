package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.model.PartRecord;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public final class PartRecordSpecifications {
    public static Specification<PartRecord> createAtAfterThan(Date date) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("createdAt"), date);
    }

    public static Specification<PartRecord> createAtBeforeThan(Date date) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("createdAt"), date);
    }

    public static Specification<PartRecord> partIdEquals(Long partId) {
        return (root, query, builder) -> builder.equal(root.get("part"), partId);
    }
}
