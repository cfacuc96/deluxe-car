package com.bootcamp.finalProject.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderRequestDTO {

    @NotNull
    @ApiModelProperty(notes = "Identifier of the distributor requesting the order.",
            example = "0012",
            position = 1)
    private Long dealerNumber;

    @NotNull
    @ApiModelProperty(notes = "Indicates the status of the order regarding its delivery",
            example = "P",
            position = 2)
    private String deliveryStatus;

    @NotNull
    @ApiModelProperty(notes = "Unique identifier of the order",
            example = "1",
            position = 3)
    private Integer order;
}
