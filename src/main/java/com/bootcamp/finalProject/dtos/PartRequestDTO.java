package com.bootcamp.finalProject.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PartRequestDTO {
    ///asd
    //TODO: que se hace con user y pass? No se mapea aca no?
    private String queryType;
    private Date date;

}
