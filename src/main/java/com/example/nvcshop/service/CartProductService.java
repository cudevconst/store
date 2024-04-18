package com.example.nvcshop.service;

import com.example.nvcshop.dto.request.CartProductRequest;
import com.example.nvcshop.dto.response.CartProductResponse;
import com.example.nvcshop.entity.Cart;
import com.example.nvcshop.entity.CartProduct;
import com.example.nvcshop.entity.Product;
import com.example.nvcshop.mapper.CartProductMapper;
import com.example.nvcshop.mapper.ProductMapper;
import com.example.nvcshop.repository.CartProductRepository;
import com.example.nvcshop.repository.CartRepository;
import com.example.nvcshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartProductService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartProductRepository cartProductRepository;

    public List<CartProductResponse> findAllByCartId(String cartId){
        List<CartProduct> cartProductList = cartProductRepository.findAllByCartId(cartId);
        List<CartProductResponse> cartProductResponses = new ArrayList<>();
        for(CartProduct cartProduct : cartProductList){
            CartProductResponse cartProductResponse = CartProductMapper.toResponse(cartProduct);
            cartProductResponse.setProduct(ProductMapper.toResponse(cartProduct.getProduct()));
            cartProductResponses.add(cartProductResponse);
        }
        return cartProductResponses;
    }
    @Transactional
    public CartProductResponse saveProductToCart(CartProductRequest cartProductRequest){
        String productId = cartProductRequest.getProductId();
        String cartId = cartProductRequest.getCartId();
        Cart cart = cartRepository.findById(cartId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);
        CartProduct cartProduct = CartProduct.builder()
                .size(cartProductRequest.getSize())
                .color(cartProductRequest.getColor())
                .quantity(cartProductRequest.getQuantity())
                .build();
        cartProduct.setCart(cart);
        cartProduct.setProduct(product);
        cartProductRepository.save(cartProduct);
        CartProductResponse cartProductResponse = CartProductMapper.toResponse(cartProduct);
        cartProductResponse.setProduct(ProductMapper.toResponse(cartProduct.getProduct()));

        return cartProductResponse;
    }

    @Transactional
    public Boolean deleteProduct(String id){
        Optional<CartProduct> cartProduct = cartProductRepository.findById(id);
        if(cartProduct.isPresent()){
            cartProductRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
