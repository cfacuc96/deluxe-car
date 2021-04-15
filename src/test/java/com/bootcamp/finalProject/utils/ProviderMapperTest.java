package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.ProviderDTO;
import com.bootcamp.finalProject.model.Provider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ProviderMapperTest {
    ProviderMapper mapper = new ProviderMapper();
    @Test
    void correctConvert(){
        List<ProviderDTO> expected = new ArrayList<>();

        ProviderDTO expectedIn = new ProviderDTO();

        expectedIn.setIdProvider(99L);
        expectedIn.setName("name");
        expectedIn.setCountry("Argentina");
        expectedIn.setPhone("342345566");
        expectedIn.setAddress("Av.Direccion 45");

        ProviderDTO actual = mapper.toDTO(getProviders());

        Assertions.assertEquals(expectedIn,actual);


    }

    public Provider getProviders(){
        Provider provider = new Provider(99L,"name","Av.Direccion 45","342345566","Argentina",null);

        return provider;
    }
}
