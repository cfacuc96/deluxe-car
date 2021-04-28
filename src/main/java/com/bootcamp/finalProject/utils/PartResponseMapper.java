package com.bootcamp.finalProject.utils;



import com.bootcamp.finalProject.dtos.PartPriceDTO;
import com.bootcamp.finalProject.dtos.PartRecordDTO;
import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.model.PartRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PartResponseMapper {
    SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");

    public List<PartResponseDTO> toDTO(List<Part> partList) {
        List<PartResponseDTO> ret = new ArrayList<>();

        for (Part part :
                partList) {

            ret.add(toDTO(part));
        }
        return ret;
    }

    public PartResponseDTO toDTO(Part part) {
        PartResponseDTO ret = new PartResponseDTO();

        ret.setPartCode(part.getPartCode());
        ret.setDescription(part.getDescription());
        ret.setMaker(part.getProvider().getName());
        ret.setQuantity(part.getQuantity());

        if (part.getPartRecords().size() > 0) {
            PartRecord p = part.getPartRecords().iterator().next();
            ret.setNormalPrice(p.getNormalPrice());
            ret.setUrgentPrice(p.getUrgentPrice());
            if (p.getDiscountRate() != null)
                ret.setDiscountType(p.getDiscountRate().getDiscount());
        }

        ret.setNetWeight(part.getNetWeight());
        ret.setLongDimension(part.getLongDimension());
        ret.setWidthDimension(part.getWidthDimension());
        ret.setTallDimension(part.getTallDimension());
        ret.setLastModification(datePattern.format(part.getLastModification()));

        return ret;
    }

    public PartPriceDTO toPartPriceDTO(Part part){
        PartPriceDTO result = new PartPriceDTO();
        result.setPartCode(part.getPartCode());
        result.setDescription(part.getDescription());
        result.setNetWeight(part.getNetWeight());
        result.setLongDimension(part.getLongDimension());
        result.setWidthDimension(part.getWidthDimension());
        result.setTallDimension(part.getTallDimension());
        result.setMaker(part.getProvider().getName());
        List<PartRecordDTO> partRecordDTOList = new ArrayList<>();
        for (PartRecord partRecord : part.getPartRecords()) {
            PartRecordDTO partRecordDTO = new PartRecordDTO();
            partRecordDTO.setCreatedAt(datePattern.format(partRecord.getCreatedAt()));
            partRecordDTO.setNormalPrice(partRecord.getNormalPrice());
            partRecordDTO.setUrgentPrice(partRecord.getUrgentPrice());
            partRecordDTO.setDiscountRate(DiscountRateMapper.toDTO(partRecord.getDiscountRate()));
            partRecordDTOList.add(partRecordDTO);
        }
        result.setHistoricPrice(partRecordDTOList);
        return result;
    }
}