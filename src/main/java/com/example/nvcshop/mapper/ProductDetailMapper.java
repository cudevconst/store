package com.example.nvcshop.mapper;

import com.example.nvcshop.dto.request.ProductDetailRequest;
import com.example.nvcshop.dto.request.ProductRequest;
import com.example.nvcshop.dto.response.ProductDetailResponse;
import com.example.nvcshop.dto.response.ProductResponse;
import com.example.nvcshop.entity.Product;
import com.example.nvcshop.entity.ProductDetails;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailMapper {
    public static ProductDetailResponse toResponse(ProductDetails product){
        ProductDetailResponse productDetailResponse = ProductDetailResponse.builder()
                .id(product.getId())
                .color(product.getColor())
                .size(product.getSize())
                .price(product.getPrice())
                .imageUrl1(product.getImageUrl1())
                .imageUrl2(product.getImageUrl2())
                .imageUrl3(product.getImageUrl3())
                .createAt(product.getCreateAt())
                .lastModify(product.getLastModify())
                .build();
        return productDetailResponse;
    }

    public static List<ProductDetailResponse> toListResponse(List<ProductDetails> productDetails){
        List<ProductDetailResponse> list = new ArrayList<>();
        for(ProductDetails p : productDetails){
            ProductDetailResponse productResponse = toResponse(p);
            list.add(productResponse);
        }
        return list;
    }

    public static ProductDetails toEntity(ProductDetailRequest productDetailRequest){
        ProductDetails product = ProductDetails.builder()
                .color(productDetailRequest.getColor())
                .size(productDetailRequest.getSize())
                .price(productDetailRequest.getPrice())
                .build();
        return product;
    }
}
