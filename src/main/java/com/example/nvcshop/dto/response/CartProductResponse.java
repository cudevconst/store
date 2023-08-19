package com.example.nvcshop.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartProductResponse {
    private String id;
    private String size;
    private String color;
    private Integer quantity;
    private ProductResponse product;
}
