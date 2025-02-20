package com.vantu.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vantu.project.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
