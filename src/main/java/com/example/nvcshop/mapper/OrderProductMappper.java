package com.example.nvcshop.mapper;

import com.example.nvcshop.dto.response.OrderProductResponse;
import com.example.nvcshop.dto.response.OrderResponse;
import com.example.nvcshop.entity.OrderProduct;
import lombok.*;

public class OrderProductMappper {

    public static OrderProductResponse toResponse(OrderProduct orderProduct){
        OrderProductResponse orderProductResponse = OrderProductResponse.builder()
                .id(orderProduct.getId())
                .color(orderProduct.getColor())
                .size(orderProduct.getSize())
                .quantity(orderProduct.getQuantity())
                .product(ProductMapper.toResponse(orderProduct.getProduct()))
                .build();
        return orderProductResponse;
    }
}
