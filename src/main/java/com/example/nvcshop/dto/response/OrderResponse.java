package com.example.nvcshop.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String id;
    private Integer amout;
    private AddressResponse address;

}
