package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.model.Part;

import java.util.ArrayList;
import java.util.List;

public class PartResponseMapper {

    public List<PartResponseDTO> toDTO(List<Part> partList){
        List<PartResponseDTO> ret = new ArrayList<>();

        for (Part part:
             partList) {

            ret.add(toDTO(part));
        }


        return ret;

    }

    public PartResponseDTO toDTO(Part part){
        PartResponseDTO ret = new PartResponseDTO();

        ret.setPartCode(part.getPartCode());
        ret.setDescription(part.getDescription());
        ret.setMaker(part.getProvider());
        //quantity
        //discount
        ret.setNormalPrice(part.getPartRecords().iterator().next().getNormalPrice());
        ret.setUrgentPrice(part.getPartRecords().iterator().next().getUrgentPrice());
        ret.setNetWeight(part.getNetWeight());
        ret.setLongDimension(part.getLongDimension());
        ret.setWidthDimension(part.getWidthDimension());
        ret.setTallDimension(part.getTallDimension());
        ret.setLastModification(part.getLastModification());


        return ret;
    }
}
