package com.vantu.project.service.IMPL;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vantu.project.model.Category;
import com.vantu.project.model.Product;
import com.vantu.project.repository.CategoryRepository;
import com.vantu.project.repository.ProductRepository;
import com.vantu.project.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
    
    public List<Product> getTopSellingProducts() {
        return productRepository.findTop10ByOrderBySoldQuantityDesc();
    }
    
    public List<Product> getRecentProducts() {
        return productRepository.findTop10ByCreatedAtAfter(LocalDate.now().minusDays(7));
    }
}
