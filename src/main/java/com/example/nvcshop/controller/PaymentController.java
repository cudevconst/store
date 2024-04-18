package com.example.nvcshop.controller;

import com.example.nvcshop.service.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/v1/payment")
public class PaymentController {

    @Autowired
    private VNPayService vnPayService;

    @PostMapping("/create")
    private ResponseEntity<?> createPayment(@RequestParam("productId") String productIO, HttpServletRequest request) throws UnsupportedEncodingException {
        return ResponseEntity.ok(vnPayService.createPayment(productIO, request));
    }

    @GetMapping("/payment-info")
    private ResponseEntity<?> paymentInfo(@RequestParam("vnp_ResponseCode") String responseCode,
                                          @RequestParam("vnp_TransactionStatus") String transactionStatus,
                                          @RequestParam("vnp_OrderInfo") String orderId){
        Map<Object, Object> mapResults = vnPayService.paymentInfo(responseCode, transactionStatus, orderId);
        Integer statusCode = (Integer) mapResults.get("status");
        if(statusCode == 200){
            return ResponseEntity.ok(mapResults);
        }
        else {
            return ResponseEntity.badRequest().body(mapResults);
        }
    }
}
