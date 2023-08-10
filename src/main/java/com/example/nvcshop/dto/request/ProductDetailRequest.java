package com.example.nvcshop.dto.request;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailRequest {
    private Double price;
    private String color;
    private String size;
    private MultipartFile imageUrl1;
    private MultipartFile imageUrl2;
    private MultipartFile imageUrl3;
//    private String productId;
}
