package com.example.nvcshop.controller;

import com.example.nvcshop.dto.request.ProductDetailRequest;
import com.example.nvcshop.dto.request.ProductRequest;
import com.example.nvcshop.dto.response.ProductResponse;
import com.example.nvcshop.entity.ProductDetails;
import com.example.nvcshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    private ResponseEntity<?> findById(@PathVariable("productId") String productId){
        ProductResponse productResponse = productService.findById(productId);
        if(productResponse != null){
            return ResponseEntity.ok(productResponse);
        }
        else{
            return ResponseEntity.status(400).body("Not found product");
        }
    }
    @GetMapping("/all")
    private ResponseEntity<?> findAllProduct(){
        return ResponseEntity.ok(productService.findAll());
    }
    @PostMapping("/create")
    public ResponseEntity<?> createNewProduct(@RequestParam("name") String name,
                                           @RequestParam("list")List<String> type,
                                           @RequestParam("color")String color,
                                           @RequestParam("size") String size,
                                           @RequestParam("price") Double price,
                                           @RequestPart("image1")MultipartFile file1,
                                           @RequestPart("image2")MultipartFile file2,
                                           @RequestPart("image3")MultipartFile file3) throws IOException {
        ProductRequest productRequest = ProductRequest.builder()
                .name(name)
                .build();

        ProductDetailRequest productDetails = ProductDetailRequest.builder()
                .color(color)
                .size(size)
                .price(price)
                .imageUrl1(file1)
                .imageUrl2(file2)
                .imageUrl3(file3)
                .build();
        ProductResponse productResponse = productService.createNewProduct(productRequest, type, productDetails);
        return ResponseEntity.ok(productResponse);
    }

    @PostMapping("/add/{productId}")
    private ResponseEntity<?> addProductDetail(@PathVariable("productId") String productId,
                                               @RequestParam("color")String color,
                                               @RequestParam("size") String size,
                                               @RequestParam("price") Double price,
                                               @RequestPart("image1")MultipartFile file1,
                                               @RequestPart("image2")MultipartFile file2,
                                               @RequestPart("image3")MultipartFile file3) throws IOException {
        ProductDetailRequest productDetails = ProductDetailRequest.builder()
                .color(color)
                .size(size)
                .price(price)
                .imageUrl1(file1)
                .imageUrl2(file2)
                .imageUrl3(file3)
                .build();
        ProductResponse productResponse = productService.addProductDetail(productId,productDetails);
        return ResponseEntity.ok(productResponse);
    }

    @PatchMapping("/update/{productId}")
    private ResponseEntity<?> updateProduct(@PathVariable("productId") String productId,
                                            @RequestParam("nane") String name,
                                            @RequestParam("typeId") List<String> typeIdList){
        ProductRequest productRequest = ProductRequest.builder()
                .name(name)
                .build();
        ProductResponse productResponse = productService.updateProduct(productId, productRequest, typeIdList);
        return ResponseEntity.ok(productResponse);
    }
}
