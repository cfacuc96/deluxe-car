package com.bootcamp.finalProject.dtos;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO
{
    @NotNull
    private String partCode;
    private String description;
    @NotNull @Min(value = 1)
    private Integer quantity;
    @NotEmpty
    private String accountType;
    @NotEmpty
    private String reason;
}
