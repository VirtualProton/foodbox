package com.foodbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.FoodDetail;

public interface FoodRepository extends JpaRepository<FoodDetail, Integer> {
	List<FoodDetail> findByLocation(String location);
	List<FoodDetail> findByLocationAndFoodName(String location, String foodName);
	List<FoodDetail> findById(String id);
	
}
