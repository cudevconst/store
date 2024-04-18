package com.example.nvcshop.repository;

import com.example.nvcshop.entity.KhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhoaHocRepository extends JpaRepository<KhoaHoc, Long> {
}
