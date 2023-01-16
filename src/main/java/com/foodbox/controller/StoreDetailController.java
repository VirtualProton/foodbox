package com.foodbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.foodbox.entity.StoreDetails;
import com.foodbox.service.StoreDetailService;

@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/store")
@RestController
public class StoreDetailController {
	
	@Autowired
	StoreDetailService storeDetailService;
	
	@PostMapping("/getDetails")
	public ResponseEntity<?> getDetails(@RequestBody StoreDetails storeDetail){
		return storeDetailService.findByUserName(storeDetail);
	}
	
	@PostMapping("/addDetails")
	public ResponseEntity<?> addDetails(@RequestBody StoreDetails storeDetail){
		return storeDetailService.addStoreDetails(storeDetail);
	}
}
