package com.foodbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	List<Category> findByLocation(String location);
	List<Category> findByLocationAndCategory(String location, String category);
}
