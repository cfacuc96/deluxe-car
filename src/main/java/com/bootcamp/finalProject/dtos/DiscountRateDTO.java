package com.bootcamp.finalProject.dtos;


import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DiscountRateDTO {

    private Long idDiscountRate;
    private String description;
    private String discount;


}
