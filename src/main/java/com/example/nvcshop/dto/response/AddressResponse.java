package com.example.nvcshop.dto.response;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {
    private String id;
    private String phoneNumber;
    private String city;
    private String district;
    private String commune;
    private String details;
    private Date createAt;
    private Date lastModify;
}
