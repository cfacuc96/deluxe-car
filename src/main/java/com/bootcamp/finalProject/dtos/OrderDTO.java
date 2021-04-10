package com.bootcamp.finalProject.dtos;

import lombok.*;

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
    private List<OrderDetailDTO> orderDetails;
}
