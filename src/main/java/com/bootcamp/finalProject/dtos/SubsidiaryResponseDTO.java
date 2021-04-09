package com.bootcamp.finalProject.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class SubsidiaryResponseDTO
{
    private String dealerNumber;
    private List<OrderDTO> orders;
}

