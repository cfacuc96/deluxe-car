package com.bootcamp.finalProject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProviderDTO that = (ProviderDTO) o;
        return Objects.equals(idProvider, that.idProvider) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProvider, name, address, phone, country);
    }
}
