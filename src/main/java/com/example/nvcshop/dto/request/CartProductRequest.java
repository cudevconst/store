package com.example.nvcshop.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Cart request")
public class CartProductRequest {
    @ApiModelProperty(value = "Cart ID", example = "ff7797bf-8de0-4a33-81a1-293071675959")
    private String cartId;
    @ApiModelProperty(value = "Product ID", example = "d70612f1-fa3f-4dbf-a5fe-faedaad7f8f8")
    private String productId;
    @ApiModelProperty(value = "Size", example = "S")
    private String size;
    @ApiModelProperty(value = "Color", example = "Black")
    private String color;
    @ApiModelProperty(value = "Quantity", example = "1")
    private Integer quantity;

}
