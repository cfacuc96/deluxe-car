package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.OrderRequestDTO;
import com.bootcamp.finalProject.dtos.PartRequestDTO;
import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.dtos.SubsidiaryResponseDTO;
import com.bootcamp.finalProject.exceptions.DeliveryStatusException;
import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.SubsidiaryNotFoundException;
import com.bootcamp.finalProject.exceptions.TypeOfQueryException;
import com.bootcamp.finalProject.model.Part;

import java.util.List;

public interface IPartService {
    void save(Part part);

    void delete(Long id);

    Part findById(Long id);

    List<Part> findAll();
    /**
     * Find parts depending on the type of query passed as a parameter.
     * If queryType is "C", finds all parts
     * If queryType is "P" - date Not Null, finds all parts that have been modified since the date
     * If queryType is "V" - date Not Null, finds all the parts that the price was modified from the date
     * All parts are ordered by default by their part code when no ordering is passed as a parameter
     * otherwise the ordering passed by parameter is taken.
     * @param requestDTO Not Nulleable
     *      partRequest.queryType Not Null or Empty
     *      partRequest.order Not Null
     *      partRequest.date Null if partRequest.queryType different than "C"
     * @return List of PartResponseDTO mapped from List of Part.
     * @throws TypeOfQueryException If typeOfQuery is null or is different than "C", "P", "V"
     * @throws OrderTypeException If order is differete than 0, 1, 2, 3
     */
    List<PartResponseDTO> findPart(PartRequestDTO requestDTO) throws TypeOfQueryException, OrderTypeException;

}
