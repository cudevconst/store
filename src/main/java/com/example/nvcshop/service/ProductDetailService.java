package com.example.nvcshop.service;

import com.example.nvcshop.cloudinary.FileUpload;
import com.example.nvcshop.dto.request.ProductDetailRequest;
import com.example.nvcshop.dto.request.ProductRequest;
import com.example.nvcshop.dto.response.ProductDetailResponse;
import com.example.nvcshop.entity.Product;
import com.example.nvcshop.entity.ProductDetails;
import com.example.nvcshop.mapper.ProductDetailMapper;
import com.example.nvcshop.repository.ProductDetailRepository;
import com.example.nvcshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ProductDetailService {

    @Autowired
    private FileUpload fileUpload;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Transactional
    public ProductDetails createProductDetail(ProductDetailRequest productDetailRequest) throws IOException {
        MultipartFile file1 = productDetailRequest.getImageUrl1();
        String urlFile1 = fileUpload.uploadFile(file1);
        MultipartFile file2 = productDetailRequest.getImageUrl2();
        String urlFile2 = fileUpload.uploadFile(file2);
        MultipartFile file3 = productDetailRequest.getImageUrl3();
        String urlFile3 = fileUpload.uploadFile(file3);

        ProductDetails productDetails = ProductDetailMapper.toEntity(productDetailRequest);
        productDetails.setImageUrl1(urlFile1);
        productDetails.setImageUrl2(urlFile2);
        productDetails.setImageUrl3(urlFile3);

        ProductDetails productDetailsReturn = productDetailRepository.save(productDetails);
        return productDetailsReturn;
    }
}
