package com.example.nvcshop.controller;

import com.example.nvcshop.dto.response.CartResponse;
import com.example.nvcshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/create")
    private ResponseEntity<?> createNewCart(@RequestParam("amout") Integer amout,
                                            @RequestParam("userId") String userId,
                                            @RequestParam("productDetaiId") String productDetaiId){
        CartResponse cartResponse = cartService.createNewCart(amout, userId, productDetaiId);
        return ResponseEntity.ok(cartResponse);
    }
}
