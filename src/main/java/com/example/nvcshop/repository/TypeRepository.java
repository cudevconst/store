package com.example.nvcshop.repository;

import com.example.nvcshop.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, String> {

    @Query(value = "SELECT * FROM type\n" +
            "WHERE id IN (SELECT type_id FROM type_product WHERE product_id = ?1)", nativeQuery = true)
    List<Type> findAllByProductId(String productId);
}
