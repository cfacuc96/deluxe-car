package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.PartDTO;
import com.bootcamp.finalProject.dtos.PartRequestDTO;
import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.TypeOfQueryException;
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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
    public Part newPart(PartDTO part) throws Exception {

        ///???
        /*
        if(partRepository.findByPartCode(part.getPartCode()).isPresent()){
            throw new Exception();
        }
        */

        /*

        DiscountRate discount =  discountrRateRep.finById(part.part.getDiscountId())
        if(discount == null){
            throw new notfound;
        }

        */


            /*
        Provider provider = providerRepo.findById(part.getMakerId());

        if(discount == null){
            throw new notfound;
        }   */



        DiscountRate discountRate = new DiscountRate("kkk","oqwkepowqk");
        Provider provider = new Provider("pepito","la villa","0992326", "123");

        PartRecord partRecord = new PartRecord(null, new Date(), part.getNormalPrice(), part.getSalePrice() , part.getUrgentPrice(),null,discountRate);
        List<PartRecord> listPartRecord = new ArrayList<>();
        listPartRecord.add(partRecord);

        Part r = new Part(
                null,
                part.getPartCode(),
                part.getDescription(),
                part.getWidthDimension(),
                part.getTallDimension(),
                part.getLongDimension(),
                part.getNetWeight(),
                part.getQuantity(),
                new Date(),
                listPartRecord,
                provider,
                null);

        partRecord.setPart(r);
        discountRate.setPartRecords(listPartRecord);

        r = partRepository.save(r);


        return r ;
    }

}