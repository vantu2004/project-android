package com.vantu.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vantu.project.model.Category;
import com.vantu.project.model.Product;
import com.vantu.project.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/test")
    public String test() {
    	return "test";
    }
    
    // 1. Lấy tất cả danh mục
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return this.productService.getAllCategories();
    }
    
    // 2. Lấy sản phẩm theo danh mục
    @GetMapping("/categories/{id}/products")
    public List<Product> getProductsByCategory(@PathVariable Long id) {
        return this.productService.getProductsByCategory(id);
    }
    
    // 3. Lấy 10 sản phẩm bán chạy nhất
    @GetMapping("/top-sellers")
    public List<Product> getTopSellingProducts() {
        return this.productService.getTopSellingProducts();
    }
    
    // 4. Lấy 10 sản phẩm mới trong 7 ngày gần đây
    @GetMapping("/recent")
    public List<Product> getRecentProducts() {
        return this.productService.getRecentProducts();
    }
}
