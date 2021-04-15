package com.bootcamp.finalProject.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class ErrorDTO
{
    @NotEmpty
    @ApiModelProperty(notes = "Name of the error",
            example = "400 BAD_REQUEST",
            position = 1)
    String name;

    @NotEmpty
    @ApiModelProperty(notes ="Description of the error",
            example = "The type of query does not exist",
            position = 2)
    String description;
}