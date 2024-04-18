package com.example.nvcshop.repository;

import com.example.nvcshop.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
    @Query(value = "SELECT * FROM address a where a.user_id = ?1", nativeQuery = true)
    List<Address> findAllByUserId(String userId);
}
