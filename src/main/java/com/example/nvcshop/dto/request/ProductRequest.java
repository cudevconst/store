package com.example.nvcshop.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Product request")
public class ProductRequest {
    @ApiModelProperty(value = "Name", example = "Name test")
    private String name;
    @ApiModelProperty(value = "Price", example = "12345")
    private Double price;
    @ApiModelProperty(value = "String url Image 1 when upload", example = "http://res.cloudinary.com/dfbohi05o/image/upload/v1692885495/53745c45-79ea-436f-ae40-efe8e9fba482.png")
    private String image1;
    @ApiModelProperty(value = "String url Image 2 when upload", example = "http://res.cloudinary.com/dfbohi05o/image/upload/v1692885482/c457661e-930c-4c9b-8809-1370a3c537dc.jpg")
    private String image2;
    @ApiModelProperty(value = "String url Image 3 when upload", example = "http://res.cloudinary.com/dfbohi05o/image/upload/v1692885492/c3f8d612-b7d9-40c6-a1ea-31c59d4922b7.jpg")
    private String image3;
    @ApiModelProperty(value = "List type product", example = "[\"074b3e49-b83e-4d3a-9040-65f06965a63f\"]")
    private Set<String> type;
    @ApiModelProperty(value = "List size product", example = "[\"S\", \"M\", \"L\"]")
    private Set<String> sizes;
    @ApiModelProperty(value = "List color product", example = "[\"Black\"]")
    private Set<String> colors;

}
