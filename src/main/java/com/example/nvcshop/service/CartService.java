package com.example.nvcshop.service;

import com.example.nvcshop.dto.response.CartResponse;
import com.example.nvcshop.dto.response.ProductDetail1;
import com.example.nvcshop.dto.response.ProductDetailResponse;
import com.example.nvcshop.entity.Cart;
import com.example.nvcshop.entity.ProductDetails;
import com.example.nvcshop.entity.User;
import com.example.nvcshop.repository.CartRepository;
import com.example.nvcshop.repository.ProductDetailRepository;
import com.example.nvcshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;
    public CartResponse createNewCart(Integer amount, String userId, String productDetailId){
        User user  = userRepository.getById(userId);
        ProductDetails productDetails = productDetailRepository.getById(productDetailId);
        List<ProductDetails> list = new ArrayList<>();
        list.add(productDetails);
        Cart cart = Cart.builder()
                .amount(amount)
                .products(list)
                .user(user)
                .build();

        cartRepository.save(cart);

        List<ProductDetail1> list1 = new ArrayList<>();
        for(ProductDetails p : list){
            ProductDetail1 productDetail1 = ProductDetail1.builder()
                    .id(p.getId())
                    .color(p.getColor())
                    .price(p.getPrice())
                    .size(p.getSize())
                    .imageUrl1(p.getImageUrl1())
                    .imageUrl2(p.getImageUrl2())
                    .imageUrl3(p.getImageUrl3())
                    .name(p.getProduct().getName())
                    .slug(p.getProduct().getSlug())
                    .build();
            list1.add(productDetail1);
        }
        CartResponse cartResponse = CartResponse.builder()
                .id(cart.getId())
                .amout(cart.getAmount())
                .collectionProductDetail(list1)
                .build();

        return cartResponse;
    }
}
