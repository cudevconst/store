package com.example.nvcshop.controller;

import com.example.nvcshop.dto.response.ProductResponse;
import com.example.nvcshop.entity.Product;
import com.example.nvcshop.entity.TypeProduct;
import com.example.nvcshop.mapper.ProductMapper;
import com.example.nvcshop.repository.ProductRepository;
import com.example.nvcshop.service.ProductDetailService;
import com.example.nvcshop.service.ProductService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/test")
public class TestController {

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;


    @GetMapping("/{productId}")
    private ResponseEntity<?> findById(@PathVariable("productId") String productId){
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            ProductResponse productResponse = ProductMapper.toResponse(product);
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.ok("fails");
    }
    @PostMapping("/upload")
    private ResponseEntity<?> get(@RequestPart("file")MultipartFile file) throws IOException {
//        String urlFile = productDetailService.saveFile(file);
        return ResponseEntity.ok("2");
    }
}
