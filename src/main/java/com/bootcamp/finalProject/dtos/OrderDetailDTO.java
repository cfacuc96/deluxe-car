package com.bootcamp.finalProject.dtos;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO
{
    private String partCode;
    private String description;
    private Integer quantity;
    private String reason;
}
