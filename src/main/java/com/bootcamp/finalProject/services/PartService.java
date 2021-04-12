package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.PartDTO;
import com.bootcamp.finalProject.dtos.PartRequestDTO;
import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.exceptions.IncorrectParamsGivenException;
import com.bootcamp.finalProject.dtos.DiscountRateDTO;
import com.bootcamp.finalProject.dtos.ProviderDTO;
import com.bootcamp.finalProject.exceptions.DiscountRateIDNotFoundException;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.ProviderIdNotFoundException;
import com.bootcamp.finalProject.exceptions.TypeOfQueryException;
import com.bootcamp.finalProject.mnemonics.ExceptionMessage;
import com.bootcamp.finalProject.mnemonics.QueryType;
import com.bootcamp.finalProject.model.DiscountRate;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.model.PartRecord;
import com.bootcamp.finalProject.model.Provider;
import com.bootcamp.finalProject.repositories.IProviderRepository;
import com.bootcamp.finalProject.repositories.DiscountRateRepository;
import com.bootcamp.finalProject.repositories.PartRepository;
import com.bootcamp.finalProject.utils.DiscountRateMapper;
import com.bootcamp.finalProject.utils.PartResponseMapper;
import com.bootcamp.finalProject.utils.ProviderMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.bootcamp.finalProject.utils.ValidationPartUtils.POrderTypeValidation;
import static com.bootcamp.finalProject.utils.ValidationPartUtils.typeOfQueryValidation;

@Service
public class PartService implements IPartService {

    PartResponseMapper mapper = new PartResponseMapper();

    @Autowired
    private PartRepository partRepository;

    @Autowired
    IProviderRepository providerRepository;

    @Autowired
    private DiscountRateRepository discountRateRepository;

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
    public void updatePart(PartDTO partDTO) throws InternalExceptionHandler {
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

    private void validateAndUpdate(Part part, PartDTO partDTO) throws InternalExceptionHandler {
        boolean update = false;
        Date now = new Date();
        if (partDTO.getDescription() != null) {
            part.setDescription(partDTO.getDescription());
            update = true;
        }
        if (partDTO.getQuantity() != null) {
            part.setQuantity(partDTO.getQuantity());
            update = true;
        }
        if (partDTO.getNetWeight() != null) {
            part.setNetWeight(partDTO.getNetWeight());
            update = true;
        }
        if (partDTO.getLongDimension() != null) {
            part.setLongDimension(partDTO.getLongDimension());
            update = true;
        }
        if (partDTO.getWidthDimension() != null) {
            part.setWidthDimension(partDTO.getWidthDimension());
            update = true;
        }
        if (partDTO.getTallDimension() != null) {
            part.setTallDimension(partDTO.getTallDimension());
            update = true;
        }
        if (partDTO.getNormalPrice() != null || partDTO.getSalePrice() != null ||
                partDTO.getUrgentPrice() != null || partDTO.getDiscountId() != null) {
            part.getPartRecords().size();
            PartRecord partRecordAux = part.getPartRecords().get(0); //El ultimo precio (partRecord)
            PartRecord partRecord = new PartRecord(partRecordAux.getNormalPrice(),
                    partRecordAux.getSalePrice(),
                    partRecordAux.getUrgentPrice(),
                    partRecordAux.getPart(),
                    partRecordAux.getDiscountRate());
            if (partDTO.getNormalPrice() != null) {
                partRecord.setNormalPrice(partDTO.getNormalPrice());
            }
            if (partDTO.getSalePrice() != null) {
                partRecord.setSalePrice(partDTO.getSalePrice());
            }
            if (partDTO.getUrgentPrice() != null) {
                partRecord.setUrgentPrice(partDTO.getUrgentPrice());
            }
            if (partDTO.getDiscountId() != null) {
                DiscountRate discountRate = findDiscountRateById(partDTO.getDiscountId());
                partRecord.setDiscountRate(discountRate);
            }
            partRecord.setCreatedAt(now);
            partRecord.setPart(part);
            part.getPartRecords().add(partRecord);
            update = true;
        }
        if (partDTO.getMakerId() != null) {
            Provider provider = findProviderById(partDTO.getMakerId());
            part.setProvider(provider);
            update = true;
        }
        if (update) {
            part.setLastModification(now);
        }
    }

    public List<ProviderDTO> findAllProviders(){
        List<ProviderDTO> providersDTO;
        List<Provider> providers =  providerRepository.findAll();
        providersDTO = providers.stream().map(ProviderMapper::toDTO).collect(Collectors.toList());
        return providersDTO;
    }

    public Provider findProviderById(Long id) throws InternalExceptionHandler {
        return providerRepository.findById(id).orElseThrow(ProviderIdNotFoundException::new);
    }

    public void saveProvider(ProviderDTO providerDTO) {
        providerRepository.save(new ModelMapper().map(providerDTO, Provider.class));
    }
    
    @Override
    public List<DiscountRateDTO> findALLDiscountRate() {
        List<DiscountRate> discountRates = discountRateRepository.findAll();
        List<DiscountRateDTO> discountRateDTOS = discountRates.stream().map(DiscountRateMapper::toDTO).collect(Collectors.toList());
        return discountRateDTOS;
    }

    @Override
    public DiscountRate findDiscountRateById(Long id) throws InternalExceptionHandler {
        return discountRateRepository.findById(id).orElseThrow(DiscountRateIDNotFoundException::new);
    }

    @Override
    public void saveDiscountRate(DiscountRateDTO discountRateDTO) {
        discountRateRepository.save(new ModelMapper().map(discountRateDTO, DiscountRate.class));
    }

}