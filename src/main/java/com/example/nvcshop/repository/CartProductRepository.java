package com.example.nvcshop.repository;

import com.example.nvcshop.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, String> {
    List<CartProduct> findAllByCartId(String cartId);
}
