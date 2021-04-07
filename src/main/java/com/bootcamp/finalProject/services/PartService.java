package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.model.Part;

import java.util.List;

public interface PartService {
    void save(Part part);

    void delete(Long id);

    Part findById(Long id);

    List<Part> findAll();
}
