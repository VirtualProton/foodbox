package com.foodbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.entity.FoodDetail;
import com.foodbox.service.FoodService;

@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/food")
@RestController
public class FoodController {
	
	@Autowired
	FoodService foodService;
	
	@PostMapping("/get_food")
	public ResponseEntity<?> getCategoryByLocation(@RequestBody FoodDetail foodDetail) {
		return foodService.findByLocation(foodDetail.getLocation());
	}
	@PostMapping("/add_food")
	public ResponseEntity<?> addFood(@RequestBody FoodDetail foodDetail) {
		return foodService.addFood(foodDetail);
	}
	@PostMapping("/update_food")
	public ResponseEntity<?> updateFood(@RequestBody FoodDetail foodDetail) {
		return foodService.updateFood(foodDetail);
	}
	@PostMapping("/delete_food")
	public ResponseEntity<?> deleteFood(@RequestBody FoodDetail foodDetail) {
		return foodService.deleteFood(foodDetail);
	}
}
