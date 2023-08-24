package com.example.nvcshop.repository;

import com.example.nvcshop.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, String> {
    @Query(value = "SELECT * from order_product\n" +
            "WHERE order_product.oder_id \n" +
            "IN (select id FROM orders WHERE user_id = ?1)", nativeQuery = true)
    List<OrderProduct> findAllByUserId(String userId);
}
