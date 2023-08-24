package com.example.nvcshop.dto.request;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private Double price;
    private String image1;
    private String image2;
    private String image3;
    private Set<String> type;
    private Set<String> sizes;
    private Set<String> colors;

}
