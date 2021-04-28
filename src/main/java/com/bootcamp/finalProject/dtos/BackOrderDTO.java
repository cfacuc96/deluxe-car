package com.bootcamp.finalProject.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
@EqualsAndHashCode
public class BackOrderDTO {
    @ApiModelProperty(notes = "Unique identifier of the  Back Order out of the Api \"subsidiaryId-backOrderID\"",
            example = "0001-00000001",
            position = 1)
    private String backOrderNumberCM;
    @ApiModelProperty(notes = "Unique identifier of the Order out of the Api \"subsidiaryId-backOrderID\"",
            example = "0001-00000001",
            position = 1)
    private String orderNumberCM;
    @ApiModelProperty(notes = "Date when the back order were created",
            example = "2021-07-12",
            position = 2)
    private String backOrderDate;
    @ApiModelProperty(notes = "Date when the back order were finish",
            example = "2021-07-12",
            position = 3)
    private String finishBackOrderDate;
    @ApiModelProperty(notes = "Status of Back Order",
            example = "P",
            position = 4)
    private String backOrderStatus;
    @NotNull(message = "backOrderPriority cant be null")
    @ApiModelProperty(notes = "Priority of Back Order",
            example = "H",
            required = true,
            position = 5)
    private String backOrderPriority;
    @NotNull(message = "backOrderDetail cant be null")
    @Valid
    @ApiModelProperty(notes = "Details of the Back Order",
            required = true,
            position = 6)
    private BackOrderDetailDTO backOrderDetail;
}
