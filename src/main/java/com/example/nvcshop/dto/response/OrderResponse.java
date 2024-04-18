package com.example.nvcshop.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String id;
    private Double amout;
    private String status;
    private AddressResponse address;
    private OrderProductResponse orderProduct;

}
