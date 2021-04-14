package com.bootcamp.finalProject.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PartDTO {

    @NotNull(message = "partCode cant be null")
    private Integer partCode;

    @NotEmpty(message = "description cant be empty")
    private String description;

    @NotNull(message = "quantity cant be empty")
    @Min(value= 1 , message = "min quantity must be 1")
    private Integer quantity;

    @NotNull(message = "netWeight cant be empty")
    @Min(value= 1, message = "netWeight quantity must be 1" )
    private Integer netWeight;

    @NotNull(message = "longDimension cant be empty")
    @Min(value= 1, message = "longDimension quantity must be 1" )
    private Integer longDimension;

    @NotNull(message = "widthDimension cant be empty")
    @Min(value= 1, message = "widthDimension quantity must be 1" )
    private Integer widthDimension;

    @NotNull(message = "tallDimension cant be empty")
    @Min(value= 1, message = "tallDimension quantity must be 1" )
    private Integer tallDimension;

    @NotNull(message = "normalPrice cant be empty")
    @Min(value= 1, message = "normalPrice quantity must be 1" )
    private Double normalPrice;

    @NotNull(message = "salePrice cant be empty")
    @Min(value= 1, message = "salePrice quantity must be 1" )
    private Double salePrice;

    @NotNull(message = "urgentPrice cant be empty")
    @Min(value= 1, message = "urgentPrice quantity must be 1" )
    private Double urgentPrice;


    @NotNull
    private Long makerId;
    @NotNull
    private Long discountId;
}
