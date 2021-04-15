package com.bootcamp.finalProject.dtos;

import lombok.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Basic fields of a provider")
public class ProviderDTO {

    @ApiModelProperty(  notes = "id of the part provider",
            example = "093843",
            position = 0)
    private Long idProvider;
    @NotNull( message = "Name is required")
    @ApiModelProperty(  notes = "Name of the part provider",
            example = "BMW",
            required = true,
            position = 1)
    private String name;

    @NotNull( message = "Address is required")
    @ApiModelProperty(  notes = "Address of the part provider",
            example = "Street 123, Office 205",
            required = true,
            position = 2)
    private String address;

    @NotNull( message = "Phone number is required")
    @ApiModelProperty(  notes = "Phone number of the part provider",
            example = "+1 567 8955",
            required = true,
            position = 3)
    private String phone;

    @NotNull( message = "Country is required")
    @ApiModelProperty(  notes = "Country of the part provider",
            example = "Nigeria",
            required = true,
            position = 4)
    private String country;

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
