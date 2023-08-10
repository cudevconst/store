package com.example.nvcshop.repository;

import com.example.nvcshop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, String> {
    @Query(value = "select * from cart c where c.user_id = ?1", nativeQuery = true)
    List<Cart> findCartByUserId(String userId);
}
