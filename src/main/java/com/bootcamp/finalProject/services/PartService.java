package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.PartRequestDTO;
import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.dtos.ProviderDTO;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.ProviderIdNotFoundException;
import com.bootcamp.finalProject.exceptions.TypeOfQueryException;
import com.bootcamp.finalProject.mnemonics.QueryType;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.model.Provider;
import com.bootcamp.finalProject.repositories.IProviderRepository;
import com.bootcamp.finalProject.repositories.PartRepository;
import com.bootcamp.finalProject.utils.PartResponseMapper;
import com.bootcamp.finalProject.utils.ProviderMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.ProviderException;
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
    IProviderRepository providerRepository;

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

}