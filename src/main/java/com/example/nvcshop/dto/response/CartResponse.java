package com.example.nvcshop.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private String id;
    private List<ProductResponse> productResponse;

}
