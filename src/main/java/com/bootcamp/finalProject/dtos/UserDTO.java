package com.bootcamp.finalProject.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel(description = "Basic fields to create or update a User \n")
public class UserDTO {

    @NotEmpty
    @ApiModelProperty(notes = "Username used for authenticate", required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(notes = "Password used for authenticate", required = true)
    private String password;

}
