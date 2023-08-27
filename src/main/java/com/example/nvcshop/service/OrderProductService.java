package com.example.nvcshop.service;

import com.example.nvcshop.dto.request.OrderRequest;
import com.example.nvcshop.dto.response.OrderProductResponse;
import com.example.nvcshop.dto.response.OrderResponse;
import com.example.nvcshop.entity.*;
import com.example.nvcshop.mapper.AddressMapper;
import com.example.nvcshop.mapper.OrderProductMappper;
import com.example.nvcshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderProductService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest){
        String userId = orderRequest.getUserId();
        String addressId = orderRequest.getAddressId();
        String productId = orderRequest.getProductId();
        System.out.println(userId + " " + addressId + " " + productId);
        User user = userRepository.findById(userId).orElse(null);
        Address address = addressRepository.findById(addressId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        Order order = Order.builder()
                .status("Ordered")
                .address(address)
                .user(user)
                .build();
        orderRepository.save(order);

        OrderProduct orderProduct = OrderProduct.builder()
                .color(orderRequest.getColor())
                .size(orderRequest.getSize())
                .quantity(orderRequest.getQuantity())
                .order(order)
                .product(product)
                .build();

        orderProductRepository.save(orderProduct);
        order.setAmount(product.getPrice() * orderRequest.getQuantity());
        OrderResponse orderResponse = OrderResponse.builder()
                .id(order.getId())
                .amout(order.getAmount())
                .status(order.getStatus())
                .address(AddressMapper.toResponse(address))
                .orderProduct(OrderProductMappper.toResponse(orderProduct))
                .build();
        return orderResponse;
    }

    public List<OrderResponse> findAllByUserId(String userId){
        List<OrderProduct> list = orderProductRepository.findAllByUserId(userId);
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for(OrderProduct orderProduct : list){
            Order order = orderProduct.getOrder();
            Product product = orderProduct.getProduct();
            OrderResponse orderResponse = OrderResponse.builder()
                    .id(order.getId())
                    .amout(order.getAmount())
                    .status(order.getStatus())
                    .address(AddressMapper.toResponse(order.getAddress()))
                    .orderProduct(OrderProductMappper.toResponse(orderProduct))
                    .build();
            orderResponseList.add(orderResponse);
        }
        return orderResponseList;
    }

    @Transactional
    public Map<Object, Object> updateStatus(String orderId, String status){
        Map<Object, Object> map =  new HashMap<>();
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(orderOptional.isPresent()){
            Order order = orderOptional.get();
            order.setStatus(status);
            orderRepository.save(order);
            map.put("message", "Change success");
            map.put("status", status);
            return map;
        }
        else{
            map.put("message", "Not exitst order");
            return map;
        }
    }
}
