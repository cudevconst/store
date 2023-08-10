package com.example.nvcshop.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetail1 {
    private String id;
    private String name;
    private String slug;
    private Double price;
    private String color;
    private String size;
    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;
}
