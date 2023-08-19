package com.example.nvcshop.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartProductRequest {
    private String cartId;
    private String productId;
    private String size;
    private String color;
    private Integer quantity;

}
