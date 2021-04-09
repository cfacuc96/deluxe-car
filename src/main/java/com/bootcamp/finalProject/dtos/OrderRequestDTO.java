package com.bootcamp.finalProject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderRequestDTO {

    private Long dealerNumber;
    private String deliveryStatus;
    private Integer order;
}
