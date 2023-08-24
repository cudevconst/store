package com.example.nvcshop.service;

import com.example.nvcshop.entity.Order;
import com.example.nvcshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;


}
