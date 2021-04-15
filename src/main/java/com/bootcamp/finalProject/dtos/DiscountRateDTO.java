package com.bootcamp.finalProject.dtos;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DiscountRateDTO {

    @ApiModelProperty(notes = "Unique identifier of the DiscountRate. Each discount has its own id",
            example = "54321",
            position = 1)
    private Long idDiscountRate;

    @NotEmpty
    @ApiModelProperty(notes = "Description of the DiscountRate",
            example = "Discount for part Espolon BMW 320i",
            position = 2)
    private String description;

    @NotEmpty
    @ApiModelProperty(notes = "Value of the Discount",
            example = "%30",
            position = 3)
    private String discount;

}
