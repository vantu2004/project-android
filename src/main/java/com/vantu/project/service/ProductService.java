package com.vantu.project.service;

import java.util.List;

import com.vantu.project.model.Category;
import com.vantu.project.model.Product;

public interface ProductService {
	List<Category> getAllCategories();
	
	List<Product> getProductsByCategory(Long categoryId);
	
	List<Product> getTopSellingProducts();
	
	List<Product> getRecentProducts();
}
