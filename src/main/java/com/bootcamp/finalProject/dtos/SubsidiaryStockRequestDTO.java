package com.bootcamp.finalProject.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel(description = "fields for a query of stock in a subsidiary")
public class SubsidiaryStockRequestDTO {

    @NotNull( message = "DealerNumber is required")
    @ApiModelProperty(  notes = "identifier of the dealer",
            example = "34",
            required = true,
            position = 1)
    private Long dealerNumber;
}
