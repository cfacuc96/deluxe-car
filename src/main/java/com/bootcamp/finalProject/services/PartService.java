package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.DiscountRateDTO;
import com.bootcamp.finalProject.dtos.PartRequestDTO;
import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.exceptions.DiscountRateIDNotFoundException;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.TypeOfQueryException;
import com.bootcamp.finalProject.mnemonics.QueryType;
import com.bootcamp.finalProject.model.DiscountRate;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.repositories.DiscountRateRepository;
import com.bootcamp.finalProject.repositories.PartRepository;
import com.bootcamp.finalProject.utils.DiscountRateMapper;
import com.bootcamp.finalProject.utils.PartResponseMapper;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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