package com.example.nvcshop.controller;

import com.example.nvcshop.dto.request.CartProductRequest;
import com.example.nvcshop.dto.response.CartProductResponse;
import com.example.nvcshop.service.CartProductService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cart")
public class CartProductController {

    @Autowired
    private CartProductService cartProductService;

    @GetMapping("/{cart_id}")
    private ResponseEntity<?> findAllById(@PathVariable("cart_id") @Parameter(example = "ff7797bf-8de0-4a33-81a1-293071675959") String id){
        return ResponseEntity.ok(cartProductService.findAllByCartId(id));
    }
    @PostMapping("/add")
    private ResponseEntity<?> addProductToCart(@RequestBody CartProductRequest cartProductRequest){
        CartProductResponse cartProductResponse = cartProductService.saveProductToCart(cartProductRequest);
        return ResponseEntity.ok(cartProductResponse);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteById(@PathVariable("id") String id){
        Boolean deleteProduct = cartProductService.deleteProduct(id);
        Map<Object, Object> map = new HashMap<>();
        if(deleteProduct){
            map.put("message", "Delete success");
            return ResponseEntity.ok(map);
        }
        else{
            map.put("message", "Delete fail");
            return ResponseEntity.status(400).body(map);
        }
    }
}
