package com.example.nvcshop.repository;

import com.example.nvcshop.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetails, String> {
    List<ProductDetails> findAllByProductId(String productId);
}
