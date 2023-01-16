package com.foodbox.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodbox.entity.FoodDetail;
import com.foodbox.repository.FoodRepository;

@Service
public class FoodService {
	
	@Autowired
	FoodRepository foodRepository;
	
	public ResponseEntity<?> findByLocation(String location) {
		try{
			List<FoodDetail> foodDetail =  foodRepository.findByLocation(location);
			if(foodDetail.size()>0) {
				return ResponseEntity.status(200).body(Collections.singletonMap("data",foodDetail));
			}else {
				return ResponseEntity.status(200).body(Collections.singletonMap("data", "No food avilables at this location"));
			}
		}catch(Exception e) {
			return ResponseEntity.status(400).body(Collections.singletonMap("err",e.fillInStackTrace()));
		}
	} 
	
	public ResponseEntity<?> addFood(FoodDetail foodDetail) {
		try{
			List<FoodDetail> foodlist = foodRepository.findByLocationAndFoodName(foodDetail.getLocation(),foodDetail.getFoodName());
//			System.out.print(foodlist);
			if(!foodlist.isEmpty()) {
				return ResponseEntity.status(200).body(Collections.singletonMap("message","Food already resgitered in database"));
			}else {
				foodRepository.save(foodDetail);
				return ResponseEntity.status(200).body(Collections.singletonMap("message","Food added successfully"));
			}
		} catch(Exception e) {
			 return ResponseEntity.status(400).body(Collections.singletonMap("error", e.getCause()));
		}	
	}
	
	public ResponseEntity<?> updateFood(FoodDetail foodDetail){
		try {
//			List<FoodDetail> listModel = foodRepository.findByLocation(foodDetail.getLocation());
			List<FoodDetail> listModel = foodRepository.findById(foodDetail.getId());

			if(!listModel.isEmpty()) {
				FoodDetail updatedFoodDetail = listModel.get(0);
				updatedFoodDetail.setFoodName(foodDetail.getFoodName()!= null ? foodDetail.getFoodName():updatedFoodDetail.getFoodName());
				updatedFoodDetail.setDiscription(foodDetail.getDiscription()!= null ? foodDetail.getDiscription():updatedFoodDetail.getDiscription());
				updatedFoodDetail.setType(foodDetail.getType()!= null ? foodDetail.getType():updatedFoodDetail.getType());
				updatedFoodDetail.setCategory(foodDetail.getCategory()!= null ? foodDetail.getCategory():updatedFoodDetail.getCategory());
				updatedFoodDetail.setAvialable(foodDetail.isAvialable());
				updatedFoodDetail.setImgUrl(foodDetail.getImgUrl()!= null ? foodDetail.getImgUrl():updatedFoodDetail.getImgUrl());
				foodRepository.save(updatedFoodDetail);
				return ResponseEntity.status(200).body(Collections.singletonMap("message", "Data updated successfully"));
			}else {
				return ResponseEntity.status(200).body(Collections.singletonMap("message", "No foood avialable with this name"));

			}
			
		}catch(Exception e) {
			 return ResponseEntity.status(400).body(Collections.singletonMap("error", e.getCause()));

		}
	}
	
	public ResponseEntity<?> deleteFood(FoodDetail foodDetail){
		foodRepository.delete(foodDetail);
		return ResponseEntity.status(200).body(Collections.singletonMap("response","Data deleted successfully"));
	}
}
