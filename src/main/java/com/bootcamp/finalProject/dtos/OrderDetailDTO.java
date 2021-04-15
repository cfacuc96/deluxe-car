package com.bootcamp.finalProject.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO
{
    @NotNull
    @ApiModelProperty(notes = "Unique identifier of the Part.Two Parts cant have the same partId.", example = "98521", required = true, position = 1)
    private String partCode;

    @ApiModelProperty(  notes = "Description of the part.",
            example = "Espolon BMW 320i",
            position = 2)
    private String description;

    @NotNull @Min(value = 1)
    @ApiModelProperty(  notes = "Quantity of part",
            example = "20",
            position = 3)
    private Integer quantity;

    @NotEmpty
    @ApiModelProperty(  notes = "Type of account",
            example = "??",
            position = 4)
    private String accountType;

    @NotEmpty
    @ApiModelProperty(  notes = "Reason because the part is delayed",
            example = "20",
            position = 5)
    private String reason;
}
