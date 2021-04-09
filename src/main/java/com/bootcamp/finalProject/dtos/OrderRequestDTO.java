package com.bootcamp.finalProject.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderRequestDTO {

    private Integer dealerNumber;
    private String deliveryStatus;
    private Integer order;
}
