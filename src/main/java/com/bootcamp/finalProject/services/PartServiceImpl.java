package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.PartRequestDTO;
import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.repositories.PartRepository;
import com.bootcamp.finalProject.utils.PartResponseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements PartService {

    PartResponseMapper mapper = new PartResponseMapper();

    private final PartRepository repository;

    public PartServiceImpl(PartRepository repo) {
        this.repository = repo;
    }

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

    public List<PartResponseDTO> findPart(PartRequestDTO partRequest){
        List<Part> parts = new ArrayList<>();
        if(validarTipoConsulta(partRequest.getQueryType())){
            switch (partRequest.getQueryType()){
                case "C":
                    parts = repository.findAll();
                    break;
                case "P":
                    parts = repository.findParcialPartByLastModification(partRequest.getDate());
                    break;
                case "V":
                    parts = repository.findVariationPartByPriceCreateAt(partRequest.getDate());
                    break;
                default:
                    break;
            }
        }
//        else{
//            throw new TipoConsultaNotFoundException();
//        }
        return mapper.toDTO(parts);
    }

    public boolean validarTipoConsulta(String tipoConsulta){
        return true;
    }
}