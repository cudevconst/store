package com.example.nvcshop.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Type request")
public class TypeRequest {
    @ApiModelProperty(value = "Type name", example = "Type test")
    private String nameType;
}
