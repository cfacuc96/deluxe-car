package com.bootcamp.finalProject.dtos;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO
{
    private String orderNumberCM;
    private String orderDate;
    private String deliveryDate;
    private Integer daysDelayed;
    private String deliveryStatus;

    @NotNull
    @Valid
    private List<OrderDetailDTO> orderDetails;
}
