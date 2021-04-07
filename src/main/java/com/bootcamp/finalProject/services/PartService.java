package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.PartRequestDTO;
import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.model.Part;

import java.util.List;

public interface PartService {
    void save(Part part);

    void delete(Long id);

    Part findById(Long id);

    List<Part> findAll();

    List<PartResponseDTO> findPart(PartRequestDTO requestDTO);
}
