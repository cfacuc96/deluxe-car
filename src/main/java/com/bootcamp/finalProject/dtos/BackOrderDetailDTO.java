package com.bootcamp.finalProject.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
@EqualsAndHashCode
public class BackOrderDetailDTO {
    @NotNull(message = "partCode cant be null")
    @ApiModelProperty(notes = "Unique identifier of the Part.Two Parts cant have the same partId.",
            example = "98521",
            required = true,
            position = 1)
    private Integer partCode;

    @ApiModelProperty(notes = "Description of the part.",
            example = "Espolon BMW 320i",
            position = 2)
    private String description;

    @NotNull(message = "quantity cant be null")
    @Min(value = 1, message = "quantity min 1")
    @ApiModelProperty(notes = "Quantity of part",
            example = "20",
            required = true,
            position = 3)
    private Integer quantity;

    @NotNull(message = "accountType cant be null")
    @ApiModelProperty(  notes = "Type of account",
            example = "R",
            required = true,
            position = 4)
    private String accountType;
}
