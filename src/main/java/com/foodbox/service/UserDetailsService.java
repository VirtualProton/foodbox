package com.foodbox.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodbox.entity.UserDetails;
import com.foodbox.repository.UserDetailsRepository;

@Service
public class UserDetailsService {
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	public ResponseEntity<?> getUserDetails(UserDetails userDetail){
		try {
			List<UserDetails> userDetails = userDetailsRepository.findByUserName(userDetail.getUserName());
			if(!userDetails.isEmpty()) {
				return ResponseEntity.status(200).body(Collections.singletonMap("data",userDetails)); 
			}else {
				return ResponseEntity.status(404).body(Collections.singletonMap("messege","no data found"));
			}
		}catch(Exception e) {
			return ResponseEntity.status(400).body(Collections.singletonMap("error",e.fillInStackTrace())); 
		}
	}
	
	
	public ResponseEntity<?> addUserDetails(UserDetails userDetail){
		try {
			List<UserDetails> userDetails = userDetailsRepository.findByUserName(userDetail.getUserName());
			if(!userDetails.isEmpty()) {
				return ResponseEntity.status(400).body(Collections.singletonMap("error","Food already resgitered in database"));
			}else {
				userDetailsRepository.save(userDetail);
				return ResponseEntity.status(200).body(Collections.singletonMap("message","Food already resgitered in database"));
			}
		}catch(Exception e) {
			return ResponseEntity.status(400).body(Collections.singletonMap("eroor",e.fillInStackTrace()));
		}
	}
}
