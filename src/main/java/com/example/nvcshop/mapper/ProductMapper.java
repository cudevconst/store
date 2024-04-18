package com.example.nvcshop.mapper;

import com.example.nvcshop.dto.response.ProductResponse;
import com.example.nvcshop.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    public static ProductResponse toResponse(Product product){
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .slug(product.getSlug())
                .colors(product.getColor())
                .sizes(product.getSizes())
                .price(product.getPrice())
                .image1(product.getImage1())
                .image2(product.getImage2())
                .image3(product.getImage3())
                .typeResponses(TypeMapper.toListResponse(product.getTypes()))
                .build();
        return productResponse;
    }

//    public static List<ProductResponse> toListResponse(List<Product> list){
//        List<ProductResponse> productResponseList = new ArrayList<>();
//        for(Product p : list){
//            productResponseList.add(toResponse(p));
//        }
//        return productResponseList;
//    }

    public static List<ProductResponse> toListResponse(List<Product> products) {
        List<ProductResponse> productResponseList = new ArrayList<>();
        for(Product p : products){
            productResponseList.add(toResponse(p));
        }
        return productResponseList;
    }
}
