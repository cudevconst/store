package com.example.nvcshop.dto.response;

import lombok.*;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String slug;
    private Collection<TypeResponse> type;
    private Collection<ProductDetailResponse> productDetail;
    private Date createAt;
    private Date lastModify;
}
