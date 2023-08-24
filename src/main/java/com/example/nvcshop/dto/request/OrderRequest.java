package com.example.nvcshop.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private String userId;
    private String addressId;
    private String productId;
    private String size;
    private String color;
    private Integer quantity;

}
