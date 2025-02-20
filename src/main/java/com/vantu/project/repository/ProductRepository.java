package com.vantu.project.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vantu.project.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findTop10ByOrderBySoldQuantityDesc();
    List<Product> findTop10ByCreatedAtAfter(LocalDate date);
}
