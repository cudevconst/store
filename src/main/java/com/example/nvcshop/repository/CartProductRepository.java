package com.example.nvcshop.repository;

import com.example.nvcshop.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, String> {
    @Query(value = "select * from cart_product cp where cp.cart_id = ?1", nativeQuery = true)
    List<CartProduct> findAllByCartId(String cartId);
}
