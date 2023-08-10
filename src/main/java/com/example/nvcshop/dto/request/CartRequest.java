package com.example.nvcshop.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequest {
    private Integer amount;
    private String productDetailId;
    private String userId;
}
