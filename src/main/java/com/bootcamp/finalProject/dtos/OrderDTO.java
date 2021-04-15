package com.bootcamp.finalProject.dtos;

import io.swagger.annotations.ApiModelProperty;
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
    @NotNull(message = "Order number cant be null")
    @ApiModelProperty(notes = "Unique identifier of the Order, Each Order has its own id",
            example = "00000010",
            required = true,
            position = 1)
    private String orderNumberCM;

    @NotNull
    @ApiModelProperty(notes = "order generation date",
            example = "2021-04-01 09:17:58",
            required = true,
            position = 2)
    private String orderDate;

    @NotNull
    @ApiModelProperty(notes = "Expected date for package delivery",
            example = "2021-04-09 14:17:58",
            required = true,
            position = 3)
    private String deliveryDate;

    @NotNull
    @ApiModelProperty(notes = "Number of days delivery is late",
            example = "15",
            position = 4)
    private Integer daysDelayed;

    @NotNull
    @ApiModelProperty(notes = "Shipment delivery status",
            example = "D",
            required = true,
            position = 5)
    private String deliveryStatus;

    @NotNull
    @Valid
    @ApiModelProperty(notes = "List that stores the details of the order referring to the " +
            "parts and the quantities requested of each of them",
            position = 6)
    private List<OrderDetailDTO> orderDetails;
}
