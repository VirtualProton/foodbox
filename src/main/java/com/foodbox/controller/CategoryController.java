package com.foodbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.entity.Category;
import com.foodbox.service.CategoryService;

@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/category")
@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/get_category")
	public ResponseEntity<?> getCategoryByLocation(@RequestBody Category category) {
		return categoryService.findByLocation(category.getLocation());
	}
	@PostMapping("/add_category")
	public ResponseEntity<?> addCategoryByLocation(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}
}
