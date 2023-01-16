package com.foodbox.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodbox.entity.Category;
import com.foodbox.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public ResponseEntity<?> findByLocation(String location) {
		try{
			List<Category> category =  categoryRepository.findByLocation(location);
			if(category.size()>0) {
				return ResponseEntity.status(200).body(Collections.singletonMap("data",category));
			}else {
				return ResponseEntity.status(200).body(Collections.singletonMap("data", "No category avilables at this location"));
			}
		}catch(Exception e) {
			return ResponseEntity.status(400).body(Collections.singletonMap("err",e.fillInStackTrace()));
		}
	}
	
	public ResponseEntity<?> addCategory(Category category){
		{
			try {
				List<Category> categorylist  = categoryRepository.findByLocationAndCategory(category.getLocation(),category.getCategory());
				if(!categorylist.isEmpty()) {
					return ResponseEntity.status(200).body(Collections.singletonMap("message","Category already resgitered in database"));
				}else {
					categoryRepository.save(category);
					return ResponseEntity.status(200).body(Collections.singletonMap("message","Category added successfully"));
				}
			}catch(Exception e) {
				return ResponseEntity.status(400).body(Collections.singletonMap("error",e.fillInStackTrace()));
			}
		}
	}
}
