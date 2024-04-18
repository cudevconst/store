package com.example.nvcshop.dto.response;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String slug;
    private Double price;
    private String image1;
    private String image2;
    private String image3;

    private List<TypeResponse> typeResponses;
    private Set<String> sizes;
    private Set<String> colors;
}
