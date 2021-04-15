package com.bootcamp.finalProject.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
@ApiModel(description = "Basic fields of Part")
public class PartResponseDTO {
    @ApiModelProperty(notes = "Unique identifier of the part.Two Parts cannot have the same partId.",
            example = "98521789",
            required = true,
            position = 1)
    private Integer partCode;

    @ApiModelProperty(  notes = "Description of the part.",
            example = "Espolon BMW 320i",
            position = 3)
    private String description;

    @ApiModelProperty(  notes = "Quantity of the part",
            example = "1",
            position = 2)
    private String maker;

    @ApiModelProperty(  notes = "Stock of the part",
            example = "20",
            position = 11)
    private Integer quantity;

    @ApiModelProperty(  notes = "Type of discount of the part",
            example = "20",
            position = 7)
    private String discountType;

    @ApiModelProperty(  notes = "Normal price of the part, min 1",
            example = "20",
            position = 8)
    private Double normalPrice;

    @ApiModelProperty(  notes = "Urgent price of the part, min 1",
            example = "90",
            position = 9)
    private Double urgentPrice;

    @ApiModelProperty(  notes = "NetWeight of the part",
            example = "64",
            required = true,
            position = 4)
    private Integer netWeight;

    @ApiModelProperty(  notes = "Long dimension of the part",
            example = "69",
            position = 5)
    private Integer longDimension;

    @ApiModelProperty(  notes = "Width dimension of the part",
            example = "20",
            position = 6)
    private Integer widthDimension;

    @ApiModelProperty(  notes = "Tall dimension of the part",
            example = "30",
            position = 7)
    private Integer tallDimension;

    @ApiModelProperty(  notes = "Last time were modified the part",
            example = "30",
            position = 10)
    private String lastModification;
}
