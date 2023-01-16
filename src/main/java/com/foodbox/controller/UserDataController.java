package com.foodbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.foodbox.entity.UserDetails;
import com.foodbox.service.UserDetailsService;

@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/user")
@RestController
public class UserDataController {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@PostMapping("/get_userdetails")
	public ResponseEntity<?> getUserDetails(@RequestBody UserDetails userDetails) {
		System.out.println(userDetails);
		return userDetailsService.getUserDetails(userDetails);
	}
	@PostMapping("/add_userdetails")
	public ResponseEntity<?> addUserDetails(@RequestBody UserDetails userDetails) {
		return userDetailsService.addUserDetails(userDetails);
	}
}
