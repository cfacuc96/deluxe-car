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
@ApiModel(description = "Response of a query for a subsidiary")
public class SubsidiaryResponseDTO
{
    @ApiModelProperty(  notes = "Identifying number of the dealer",
            example = "67",
            position = 1)
    private String dealerNumber;

    @ApiModelProperty(  notes = "List of all orders associated with this dealer",
            position = 2)
    private List<OrderDTO> orders;
}

