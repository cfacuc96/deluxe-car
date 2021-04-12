package com.bootcamp.finalProject.dtos;

import com.bootcamp.finalProject.model.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderDTO {

    private Long idProvider;
    private String name;
    private String address;
    private String phone;
    private String country;
}
