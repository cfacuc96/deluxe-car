package com.bootcamp.finalProject.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class PartResponseDTO {
    private Integer partCode;
    private String description;
    private String maker;
    private Integer quantity;
    private String discountType;
    private Double normalPrice;
    private Double urgentPrice;
    private Integer netWeight;
    private Integer longDimension;
    private Integer widthDimension;
    private Integer tallDimension;
    private Date lastModification;
}
