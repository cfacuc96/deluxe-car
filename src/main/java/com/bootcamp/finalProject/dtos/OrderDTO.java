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
    @ApiModelProperty(notes = "Unique identifier of the Order out of the Api \"subsidiaryId-orderID\"",
            example = "0001-00000001",
            required = true,
            position = 1)
    private String orderNumberCM;
    @ApiModelProperty(notes = "Date when the order were created", example = "2021-04-01", position = 2)
    private String orderDate;
    @ApiModelProperty(notes = "Date the order will arrive", example = "2021-04-15", position = 3)
    private String deliveryDate;
    @ApiModelProperty(notes = "Days delayed in the delivery of the order", example = "7", position = 4)
    private Integer daysDelayed;
    @ApiModelProperty(notes = "Status of delivery", example = "P", position = 5)
    private String deliveryStatus;

    @NotNull
    @Valid
    @ApiModelProperty(notes = "Details of the order", position = 6)
    private List<OrderDetailDTO> orderDetails;
}
