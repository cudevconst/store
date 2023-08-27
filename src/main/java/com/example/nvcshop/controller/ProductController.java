package com.example.nvcshop.controller;

import com.example.nvcshop.dto.request.ProductRequest;
import com.example.nvcshop.dto.response.ProductResponse;
import com.example.nvcshop.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    private ResponseEntity<?> findAllProduct(){
        List<ProductResponse> productResponseList = productService.findAllProduct();
        return ResponseEntity.ok(productResponseList);
    }
    @GetMapping("/{id}")
    private ResponseEntity<?> getProductById(@PathVariable("id") @Parameter(example = "d70612f1-fa3f-4dbf-a5fe-faedaad7f8f8") String productId){
        ProductResponse productResponse = productService.getProduct(productId);
        if(productResponse != null){
            return ResponseEntity.ok(productResponse);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/create")
    private ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PatchMapping("/update/{id}")
    private ResponseEntity<?> updateProduct(@PathVariable("id") @Parameter(example = "d70612f1-fa3f-4dbf-a5fe-faedaad7f8f8") String id , @RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.getProduct(id);
        if(productResponse != null){
            return ResponseEntity.ok(productService.updateProduct(productRequest, id));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteProduct(@PathVariable("id") String productId){
        Boolean producct = productService.deleteProduct(productId);
        if(producct){
            return ResponseEntity.ok("Success");
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

}
