package com.example.nvcshop.dto.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequest {
    private String phoneNumber;
    private String city;
    private String district;
    private String commune;
    private String details;
}
