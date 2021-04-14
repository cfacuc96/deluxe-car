package com.bootcamp.finalProject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderDTO {

    private Long idProvider;
    private String name;
    private String address;
    private String phone;
    private String country;
    private List<ProviderPartsDTO> parts;
}
