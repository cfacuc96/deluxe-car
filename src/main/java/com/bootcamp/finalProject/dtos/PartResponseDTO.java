package com.bootcamp.finalProject.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
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
    private String lastModification;
}
