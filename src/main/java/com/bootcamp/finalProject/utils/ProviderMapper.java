package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.ProviderDTO;
import com.bootcamp.finalProject.model.Provider;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ProviderMapper {

    public static ProviderDTO toDTO(Provider provider){
        ProviderDTO providerDTO= new ProviderDTO();
        return new ModelMapper().map(provider, ProviderDTO.class);
    }
}
