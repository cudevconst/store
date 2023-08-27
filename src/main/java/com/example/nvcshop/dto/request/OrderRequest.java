package com.example.nvcshop.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Order request")
public class OrderRequest {
    @ApiModelProperty(value = "User ID", example = "272888d4-1b7a-4ced-b34c-062e8c97319e")
    private String userId;
    @ApiModelProperty(value = "Address ID", example = "97eb3dc2-67a5-42a1-b388-db76ad29a03f")
    private String addressId;
    @ApiModelProperty(value = "Product ID", example = "d70612f1-fa3f-4dbf-a5fe-faedaad7f8f8")
    private String productId;
    @ApiModelProperty(value = "Size", example = "S")
    private String size;
    @ApiModelProperty(value = "Color", example = "Black")
    private String color;
    @ApiModelProperty(value = "Quantity", example = "1")
    private Integer quantity;

}
