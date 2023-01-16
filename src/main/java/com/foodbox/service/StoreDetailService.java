package com.foodbox.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodbox.entity.StoreDetails;
import com.foodbox.repository.StoreDetailRepository;

@Service
public class StoreDetailService {
	
	@Autowired
	StoreDetailRepository storeDetailRepository;
	
	public ResponseEntity<?> findByUserName(StoreDetails storeDetail){
		try {
			List<StoreDetails> storeDetails = storeDetailRepository.findByUserName(storeDetail.getUserName());
			return ResponseEntity.status(200).body(Collections.singletonMap("data",storeDetails));
		}catch(Exception e) {
			return ResponseEntity.status(400).body(Collections.singletonMap("err",e.fillInStackTrace()));
		}
	}
	
	public ResponseEntity<?> addStoreDetails(StoreDetails storeDetail){
		try {
			List<StoreDetails> storeDetails = storeDetailRepository.findByUserName(storeDetail.getUserName());
			if(storeDetails.isEmpty()) {
				storeDetailRepository.save(storeDetail);
				return ResponseEntity.status(200).body(Collections.singletonMap("message","Data added successfully"));	
			}else {
				return ResponseEntity.status(200).body(Collections.singletonMap("message","Data already resgistered"));	
			}
		}catch(Exception e) {
			return ResponseEntity.status(400).body(Collections.singletonMap("err",e.fillInStackTrace()));
		}
	}
}
