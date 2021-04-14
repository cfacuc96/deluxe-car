package com.bootcamp.finalProject.dtos;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {


    private String orderNumberCM;
    private String orderDate;
    private String deliveryStatus;

    @NotNull
    @Valid
    private List<OrderDetailDTO> orderDetails;
}

