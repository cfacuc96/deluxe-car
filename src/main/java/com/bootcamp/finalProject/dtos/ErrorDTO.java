package com.bootcamp.finalProject.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel(description = "Basic fields to show exception message")
public class ErrorDTO
{
    @ApiModelProperty(  notes = "Name of the status.",
            example = "400 BAD_REQUEST",
            position = 1)
    String name;
    @ApiModelProperty(  notes = "Custom description of the status.",
            example = "400 BAD_REQUEST",
            position = 2)
    String description;
}