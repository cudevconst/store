package com.example.nvcshop.mapper;

import com.example.nvcshop.dto.request.ProductRequest;
import com.example.nvcshop.dto.response.ProductResponse;
import com.example.nvcshop.entity.Product;
import com.example.nvcshop.entity.TypeProduct;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    public static ProductResponse toResponse(Product product){
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .slug(product.getSlug())
                .createAt(product.getCreateAt())
                .lastModify(product.getLastModify())
                .build();
        return productResponse;
    }

    public static List<ProductResponse> toListResponse(List<Product> list){
        List<ProductResponse> productResponseList = new ArrayList<>();
        for(Product p : list){
            ProductResponse productResponse = toResponse(p);
            productResponseList.add(productResponse);
        }
        return productResponseList;
    }

    public static Product toEntity(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .build();
        return product;
    }
}
