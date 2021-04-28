package com.bootcamp.finalProject.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartRecordDTO {
    @ApiModelProperty(notes = "price creation date",
            example = "2021-04-01",
            position = 1)
    private String createdAt;
    @ApiModelProperty(notes = "Normal price of the part, min 1",
            example = "25000",
            position = 2)
    private Double normalPrice;
    @ApiModelProperty(notes = "Urgent price of the part, min 1",
            example = "90000",
            position = 3)
    private Double urgentPrice;
    @ApiModelProperty(notes = "Discount of the part",
            position = 4)
    private DiscountRateDTO discountRate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartRecordDTO that = (PartRecordDTO) o;
        return Objects.equals(createdAt, that.createdAt) && Objects.equals(normalPrice, that.normalPrice) && Objects.equals(urgentPrice, that.urgentPrice) && Objects.equals(discountRate, that.discountRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt, normalPrice, urgentPrice, discountRate);
    }

    @Override
    public String toString() {
        return "PartRecordDTO{" +
                "createdAt='" + createdAt + '\'' +
                ", normalPrice=" + normalPrice +
                ", urgentPrice=" + urgentPrice +
                ", discountRate=" + discountRate +
                '}';
    }
}
