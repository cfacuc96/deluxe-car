package com.bootcamp.finalProject.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel(description = "Basic fields to create or update a Part \n" +
        "with validation")
public class PartDTO {

    @NotNull(message = "partCode cant be null")
    @ApiModelProperty(notes = "Unique identifier of the Part.Two Parts cant have the same partId.",
            example = "98521789",
            required = true,
            position = 1)
    private Integer partCode;

    @NotEmpty(message = "description cant be empty")
    @ApiModelProperty(  notes = "Description of the part.",
                        example = "Espolon BMW 320i",
                        required = true,
                        position = 3)
    private String description;

    @NotNull(message = "quantity cant be empty")
    @Min(value= 1 , message = "min quantity must be 1")
    @ApiModelProperty(  notes = "Quantity of the part",
            example = "1",
            required = true,
            position = 2)
    private Integer quantity;

    @NotNull(message = "netWeight cant be empty")
    @Min(value= 1, message = "netWeight quantity must be 1" )
    @ApiModelProperty(  notes = "NetWeight of the part",
            example = "64",
            required = true,
            position = 4)
    private Integer netWeight;

    @NotNull(message = "longDimension cant be empty")
    @Min(value= 1, message = "longDimension quantity must be 1" )
    @ApiModelProperty(  notes = "Long dimension of the part",
            example = "69",
            required = true,
            position = 5)
    private Integer longDimension;

    @NotNull(message = "widthDimension cant be empty")
    @Min(value= 1, message = "widthDimension quantity must be 1" )
    @ApiModelProperty(  notes = "Width dimension of the part",
            example = "20",
            required = true,
            position = 6)
    private Integer widthDimension;

    @NotNull(message = "tallDimension cant be empty")
    @Min(value= 1, message = "tallDimension quantity must be 1" )
    @ApiModelProperty(  notes = "Tall dimension of the part",
            example = "30",
            required = true,
            position = 7)
    private Integer tallDimension;

    @NotNull(message = "normalPrice cant be empty")
    @Min(value= 1, message = "normalPrice quantity must be 1" )
    @ApiModelProperty(  notes = "Normal price of the part, min 1",
            example = "20",
            required = true,
            position = 7)
    private Double normalPrice;

    @NotNull(message = "salePrice cant be empty")
    @Min(value= 1, message = "salePrice quantity must be 1" )
    @ApiModelProperty(  notes = "Sale price of the part, min 1",
            example = "20",
            required = true,
            position = 8)
    private Double salePrice;

    @NotNull(message = "urgentPrice cant be empty")
    @Min(value= 1, message = "urgentPrice quantity must be 1" )
    @ApiModelProperty(  notes = "Urgent price of the part, min 1",
            example = "90",
            required = true,
            position = 9)
    private Double urgentPrice;


    @NotNull
    @ApiModelProperty(  notes = "Id of maker, should exist",
            example = "1",
            required = true,
            position = 10)
    private Long makerId;
    @NotNull
    @ApiModelProperty(  notes = "Id of discount, should exist",
            example = "1",
            required = true,
            position = 11)
    private Long discountId;
}
