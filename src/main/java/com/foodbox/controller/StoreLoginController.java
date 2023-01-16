package com.foodbox.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.entity.StoreCredential;
import com.foodbox.service.StoreService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/store")
public class StoreLoginController {
	
//	@Autowired
//	 StoreAuthenticateService  storeAuthenticateService;
	
	@Autowired
	 StoreService storeService;
	
	@PostMapping("/auth")
	public ResponseEntity<?> verifyStore(@RequestBody StoreCredential userInput ) throws Throwable{
		//System.out.print(false);
		//return ResponseEntity.status(200).body(Collections.singletonMap("data","here"));
		StoreCredential storeCred = storeService.getStoreCredential(userInput);
		if(storeCred == null) {
			return ResponseEntity.status(200).body(Collections.singletonMap("islogin",false));
		}else if(storeCred.getPassword().equals(userInput.getPassword())) {
			return ResponseEntity.status(200).body(Collections.singletonMap("islogin",true));
		}else {
			return ResponseEntity.status(200).body(Collections.singletonMap("islogin",false));
		}
//		return storeAuthenticateService.storeAuthenticate(storeCred);
	}
	
	@PostMapping("/addstore")
	public ResponseEntity<?> addStore(@RequestBody StoreCredential storeCred ) throws Throwable{
		
		return ResponseEntity.status(200).body(Collections.singletonMap("data",storeService.addStore(storeCred)));
					
	}
	
}
