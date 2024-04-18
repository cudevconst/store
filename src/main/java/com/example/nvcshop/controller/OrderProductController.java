package com.example.nvcshop.controller;

import com.example.nvcshop.dto.request.OrderRequest;
import com.example.nvcshop.dto.response.OrderProductResponse;
import com.example.nvcshop.dto.response.OrderResponse;
import com.example.nvcshop.service.OrderProductService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/order")
public class OrderProductController {

    @Autowired
    private OrderProductService orderProductService;

    @GetMapping("/{userId}")
    private ResponseEntity<?> findAllByUserId(@PathVariable("userId") @Parameter(example = "272888d4-1b7a-4ced-b34c-062e8c97319e") String userId){
        return ResponseEntity.ok(orderProductService.findAllByUserId(userId));
    }
    @PostMapping("/create")
    private ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){

        OrderResponse orderResponse = orderProductService.createOrder(orderRequest);
        return ResponseEntity.ok(orderResponse);
    }

    @PatchMapping("/update")
    private ResponseEntity<?> updateStatus(
            @RequestParam("orderId") String orderId,
            @RequestParam("status") String status){
        return ResponseEntity.ok(orderProductService.updateStatus(orderId, status));
    }
}
