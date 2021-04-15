package com.bootcamp.finalProject.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ApiModel(description = "Fields to show on the orders of a subsidiary")
public class SubsidiaryResponseDTO
{
    @ApiModelProperty(notes = "Unique identifier of the Subsidiary.Two Subsidiary cant have the same delearNumber.",
            example = "0001 or 1",
            position = 1)
    private String dealerNumber;
    @ApiModelProperty(notes = "Orders of the subsidiary", position = 2)
    private List<OrderDTO> orders;
}

