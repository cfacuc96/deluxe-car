package com.bootcamp.finalProject.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ApiModel(description = "Response given to a query of subsidiary stock")
public class SubsidiaryStockResponseDTO {

    @ApiModelProperty(  notes = "Identifier of the dealer",
            example = "5",
            position = 1)
    private String dealerNumber;

    @ApiModelProperty(  notes = "Name of the dealer",
            example = "Colombia",
            position = 2)
    private String name;

    @ApiModelProperty(notes = "List of stocks in subsidiary",
            position = 3)
    private List<SubsidiaryStockDTO> subsidiaryStocks;
}
