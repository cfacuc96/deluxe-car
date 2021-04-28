package com.bootcamp.finalProject.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartPriceDTO {
    @ApiModelProperty(notes = "Unique identifier of the Part.Two Parts cant have the same partId.",
            example = "11111112",
            position = 1)
    private Integer partCode;
    @ApiModelProperty(  notes = "Description of the part.",
            example = "Espolon BMW 320i",
            position = 2)
    private String description;
    @ApiModelProperty(  notes = "NetWeight of the part",
            example = "64",
            position = 3)
    private Integer netWeight;
    @ApiModelProperty(  notes = "Long dimension of the part",
            example = "69",
            position = 4)
    private Integer longDimension;
    @ApiModelProperty(  notes = "Width dimension of the part",
            example = "20",
            position = 5)
    private Integer widthDimension;
    @ApiModelProperty(  notes = "Tall dimension of the part",
            example = "30",
            position = 6)
    private Integer tallDimension;
    @ApiModelProperty(  notes = "The maker of the part",
            example = "1",
            position = 7)
    private String maker;
    @ApiModelProperty(notes = "Prices of the part",
            position = 8)
    private List<PartRecordDTO> historicPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartPriceDTO that = (PartPriceDTO) o;
        return Objects.equals(partCode, that.partCode) && Objects.equals(description, that.description) && Objects.equals(netWeight, that.netWeight) && Objects.equals(longDimension, that.longDimension) && Objects.equals(widthDimension, that.widthDimension) && Objects.equals(tallDimension, that.tallDimension) && Objects.equals(maker, that.maker) && Objects.equals(historicPrice, that.historicPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partCode, description, netWeight, longDimension, widthDimension, tallDimension, maker, historicPrice);
    }

    @Override
    public String toString() {
        return "PartPriceDTO{" +
                "partCode=" + partCode +
                ", description='" + description + '\'' +
                ", netWeight=" + netWeight +
                ", longDimension=" + longDimension +
                ", widthDimension=" + widthDimension +
                ", tallDimension=" + tallDimension +
                ", maker='" + maker + '\'' +
                ", historicPrice=" + historicPrice +
                '}';
    }
}
