package com.bootcamp.finalProject.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PartDTO {

    private Integer partCode;
    private String description;
    private Integer quantity;
    private Integer netWeight;
    private Integer longDimension;
    private Integer widthDimension;
    private Integer tallDimension;

    private Double normalPrice;
    private Double salePrice;
    private Double urgentPrice;


    private Long makerId;
    private Long discountId;
}
