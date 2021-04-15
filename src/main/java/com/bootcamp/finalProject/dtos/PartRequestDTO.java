package com.bootcamp.finalProject.dtos;

import com.bootcamp.finalProject.mnemonics.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ApiModel(description = "Basic fields of the query to search for parts")
public class PartRequestDTO {

    @NotNull(message = "Query type is mandatory")
    @ApiModelProperty(  notes = "Type of query to be searched for [\"C\",\"V\",\"P\"]",
            example = "C",
            required = true,
            position = 1)
    private String queryType;

    @ApiModelProperty(  notes = "Initial date of when is wanted to search for \"yyyy-mm-dd\", required if queryType = V or P",
            example = "1994-11-14",
            position = 2)
    private Date date;

    @ApiModelProperty(  notes = "Type of sorting of the resulting found parts, [1,2,3]",
            example = "2",
            position = 3)
    private Integer order;

}
