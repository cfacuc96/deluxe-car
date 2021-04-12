package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.PartDTO;
import com.bootcamp.finalProject.dtos.PartRequestDTO;
import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.exceptions.IncorrectParamsGivenException;
import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.TypeOfQueryException;
import com.bootcamp.finalProject.mnemonics.ExceptionMessage;
import com.bootcamp.finalProject.mnemonics.QueryType;
import com.bootcamp.finalProject.model.DiscountRate;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.model.PartRecord;
import com.bootcamp.finalProject.model.Provider;
import com.bootcamp.finalProject.repositories.PartRepository;
import com.bootcamp.finalProject.utils.PartResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bootcamp.finalProject.utils.ValidationPartUtils.POrderTypeValidation;
import static com.bootcamp.finalProject.utils.ValidationPartUtils.typeOfQueryValidation;

@Service
public class PartService implements IPartService {

    PartResponseMapper mapper = new PartResponseMapper();

    @Autowired
    private PartRepository partRepository;

    public List<PartResponseDTO> findPart(PartRequestDTO partRequest) throws TypeOfQueryException, OrderTypeException {
        List<Part> parts = new ArrayList<>();
        if (typeOfQueryValidation(partRequest.getQueryType())) {
            Sort sort = POrderTypeValidation(partRequest.getOrder());
            switch (partRequest.getQueryType()) {
                case QueryType.COMPLETE:
                    parts = partRepository.findAll(sort);
                    break;
                case QueryType.PARTIAL:
                    parts = partRepository.findByLastModification(partRequest.getDate(), sort);
                    break;
                case QueryType.VARIATION:
                    parts = partRepository.findByPriceCreateAt(partRequest.getDate(), sort);
                    break;
                default:
                    throw new TypeOfQueryException();
            }
        }
        return mapper.toDTO(parts);
    }

    @Override
    public void updatePart(PartDTO partDTO) throws IncorrectParamsGivenException {
        if(partDTO.getPartCode() != null){
            Part part = partRepository.findByPartCode(partDTO.getPartCode());
            if(part != null){
                validateAndUpdate(part, partDTO);
                partRepository.save(part);
            }else{
                //throw new partNotExistException
            }
        }else{
            throw new IncorrectParamsGivenException(ExceptionMessage.PART_CODE_REQUIRED);
        }
    }

    private void validateAndUpdate(Part part, PartDTO partDTO) {
        boolean update = false;
        Date now = new Date();
        if(partDTO.getDescription() != null){
            part.setDescription(partDTO.getDescription());
            update = true;
        }
        if(partDTO.getQuantity() != null){
            part.setQuantity(partDTO.getQuantity());
            update = true;
        }
        if(partDTO.getNetWeight() != null){
            part.setNetWeight(partDTO.getNetWeight());
            update = true;
        }
        if(partDTO.getLongDimension() != null){
            part.setLongDimension(partDTO.getLongDimension());
            update = true;
        }
        if(partDTO.getWidthDimension() != null){
            part.setWidthDimension(partDTO.getWidthDimension());
            update = true;
        }
        if(partDTO.getTallDimension() != null){
            part.setTallDimension(partDTO.getTallDimension());
            update = true;
        }
        if(partDTO.getNormalPrice() != null || partDTO.getSalePrice() != null ||
                partDTO.getUrgentPrice() != null || partDTO.getDiscountId() != null){
            //create partRecord
            part.getPartRecords().size();
            PartRecord partRecordAux = part.getPartRecords().get(0); //El ultimo precio (partRecord)
            PartRecord partRecord = new PartRecord(partRecordAux.getNormalPrice(),
                    partRecordAux.getSalePrice(),
                    partRecordAux.getUrgentPrice(),
                    partRecordAux.getPart(),
                    partRecordAux.getDiscountRate());
            if(partDTO.getNormalPrice() != null){
                partRecord.setNormalPrice(partDTO.getNormalPrice());
            }
            if(partDTO.getSalePrice() != null){
                partRecord.setSalePrice(partDTO.getSalePrice());
            }
            if(partDTO.getUrgentPrice() != null){
                partRecord.setUrgentPrice(partDTO.getUrgentPrice());
            }
            if(partDTO.getDiscountId() != null){
                DiscountRate discountRate = new DiscountRate();
                if(discountRate != null){
                    partRecord.setDiscountRate(discountRate);
                }else{
                    //throw new discountRateNotFoundException();
                }
            }
            partRecord.setCreatedAt(now);
            part.getPartRecords().add(partRecord);
        }
        if(partDTO.getMakerId() != null){
            //obtener marker
            Provider provider = new Provider();
            if(provider != null){
                part.setProvider(provider);
                update = true;
            }else{
                //throw new providerNotFoundException();
            }
        }
        if(update){
            part.setLastModification(now);
        }
    }

}