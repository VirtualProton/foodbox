package com.foodbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.entity.Location;
import com.foodbox.service.LocationService;

@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/location")
@RestController
public class LocationController {
	
	@Autowired
	LocationService locationService;
	
	@GetMapping("/get_location")
	public ResponseEntity<?> getAllLocation(){
		return locationService.getAllLocation();
	}
	
	@PostMapping("/add_location")
	public ResponseEntity<?> addLocation(@RequestBody Location location){
//		System.out.println(location);
//		return null;
		return locationService.addLocation(location);
	}
	
}
