package com.bootcamp.finalProject.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubsidiaryStockResponseDTO {

    private String dealerNumber;
    private String name;

    private List<SubsidiaryStockDTO> subsidiaryStocks;
}
