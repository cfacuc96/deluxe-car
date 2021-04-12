package com.bootcamp.finalProject.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PartDTO {

    private Integer partCode;
    @NotNull
    private String description;
    @NotNull
    @Min(value= 1 )
    private Integer quantity;
    @NotNull
    @Min(value= 1 )
    private Integer netWeight;
    @NotNull
    @Min(value= 1 )
    private Integer longDimension;
    @NotNull
    @Min(value= 1 )
    private Integer widthDimension;
    @NotNull
    @Min(value= 1 )
    private Integer tallDimension;

    @NotNull
    @Min(value= 1 )
    private Double normalPrice;
    @NotNull
    @Min(value= 1 )
    private Double salePrice;
    @NotNull
    @Min(value= 1 )
    private Double urgentPrice;


    @NotNull
    private Long makerId;
    @NotNull
    private Long discountId;
}
