package com.bootcamp.finalProject.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PartRequestDTO {

    private String queryType;
    private Date date;
    private Integer order;

}
