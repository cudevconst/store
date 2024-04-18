package com.example.nvcshop.mapper;

import com.example.nvcshop.dto.request.CartProductRequest;
import com.example.nvcshop.dto.response.CartProductResponse;
import com.example.nvcshop.entity.CartProduct;

public class CartProductMapper {
    public static CartProductResponse toResponse(CartProduct cartProduct){
        CartProductResponse cartProductResponse = CartProductResponse.builder()
                .id(cartProduct.getId())
                .size(cartProduct.getSize())
                .color(cartProduct.getColor())
                .quantity(cartProduct.getQuantity())
                .build();
        return cartProductResponse;
    }

    public static CartProduct toEntity(CartProductRequest cartProductRequest){
        CartProduct cartProduct = CartProduct.builder()
                .size(cartProductRequest.getSize())
                .color(cartProductRequest.getColor())
                .quantity(cartProductRequest.getQuantity())
                .build();
        return cartProduct;
    }
}
