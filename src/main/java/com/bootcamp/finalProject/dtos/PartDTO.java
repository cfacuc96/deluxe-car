package com.bootcamp.finalProject.dtos;

import lombok.Getter;
import lombok.Setter;

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
    private Integer quantity;
    @NotNull
    private Integer netWeight;
    @NotNull
    private Integer longDimension;
    @NotNull
    private Integer widthDimension;
    @NotNull
    private Integer tallDimension;

    @NotNull
    private Double normalPrice;
    @NotNull
    private Double salePrice;
    @NotNull
    private Double urgentPrice;


    @NotNull
    private Long makerId;
    @NotNull
    private Long discountId;
}
