package com.example.nvcshop.dto.response;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private String id;
    private Integer amout;
    private Collection<ProductDetail1> collectionProductDetail;

}
