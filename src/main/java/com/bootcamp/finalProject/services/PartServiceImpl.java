package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.PartRequestDTO;
import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.TypeOfQueryException;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.mnemonics.QueryType;
import com.bootcamp.finalProject.repositories.PartRepository;
import com.bootcamp.finalProject.utils.PartResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.bootcamp.finalProject.utils.ValidationPartUtils.orderTypeValidation;
import static com.bootcamp.finalProject.utils.ValidationPartUtils.typeOfQueryValidation;

@Service
public class PartServiceImpl implements PartService {

    PartResponseMapper mapper = new PartResponseMapper();

    @Autowired
    private PartRepository repository;

    @Override
    public void save(Part part) {
        repository.save(part);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Part findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Part> findAll() {
        return repository.findAll();
    }

    public List<PartResponseDTO> findPart(PartRequestDTO partRequest) throws TypeOfQueryException, OrderTypeException {
        List<Part> parts = new ArrayList<>();
        if(typeOfQueryValidation(partRequest.getQueryType())){
            Sort sort = orderTypeValidation(partRequest.getOrder());
            switch (partRequest.getQueryType()){
                case QueryType.COMPLETE:
                    parts = repository.findAll(sort);
                    break;
                case QueryType.PARTIAL:
                    parts = repository.findByLastModification(partRequest.getDate(), sort);
                    break;
                case QueryType.VARIATION:
                    parts = repository.findByPriceCreateAt(partRequest.getDate(), sort);
                    break;
                default:
                    throw new TypeOfQueryException();
            }
        }
        return mapper.toDTO(parts);
    }

}