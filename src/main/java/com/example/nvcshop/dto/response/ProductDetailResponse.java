package com.example.nvcshop.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailResponse {
    private String id;

    private Double price;
    private String color;
    private String size;
    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;
    private Date createAt;
    private Date lastModify;
}
