package com.example.nvcshop.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductResponse {
    private String id;
    private Integer quantity;

    private String size;

    private String color;

    private ProductResponse product;

}
