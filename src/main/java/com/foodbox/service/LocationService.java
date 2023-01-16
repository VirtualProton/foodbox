package com.foodbox.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodbox.entity.Category;
import com.foodbox.entity.Location;
import com.foodbox.repository.LocationRepository;

@Service
public class LocationService {
	@Autowired
	LocationRepository locationRepository;
	
	public ResponseEntity<?>getAllLocation(){
		try {
			List<Location> location = locationRepository.findAll();
			return ResponseEntity.status(200).body(Collections.singletonMap("data",location));

		}catch(Exception e) {
			return ResponseEntity.status(400).body(Collections.singletonMap("err",e.fillInStackTrace()));
		}
	}
	
	public ResponseEntity<?> addLocation(Location location){
		{
			try {
//			System.out.println(location);
				List<Location> LocationList  = locationRepository.findByLocation(location.getLocation());
				if(!LocationList.isEmpty()) {
					return ResponseEntity.status(200).body(Collections.singletonMap("message","Location already resgitered in database"));
				}else {
					locationRepository.save(location);
					return ResponseEntity.status(200).body(Collections.singletonMap("message","Location added successfully"));
				}
			}catch(Exception e) {
				return ResponseEntity.status(400).body(Collections.singletonMap("error",e.fillInStackTrace()));
			}
		}
	}
}
