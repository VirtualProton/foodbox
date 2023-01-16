//package com.foodbox.service;
//
//import java.util.Collections;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.stereotype.Service;
//
//import com.foodbox.dto.VerifiedCredential;
//import com.foodbox.entity.StoreCredential;
//import com.foodbox.util.JwtUtil;
//
//@Service
//public class StoreAuthenticateService {
//	@Autowired
//	private JwtUtil jwtUtil;
//	
////	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	@Autowired
////	private StoreService storeService;
//	
////	VerifiedCredential verifiedCredential = new VerifiedCredential();
//	
////	public ResponseEntity<?> storeAuthenticate(StoreCredential storeCred){
////		System.out.println(storeCred);
//////				try {
//////			authenticationManager.authenticate(
//////					 new UsernamePasswordAuthenticationToken(storeCred.getUserName(),storeCred.getPassword())
//////					);
//////			
//////		}catch(Exception e) {
//////			return ResponseEntity.status(401).body(Collections.singletonMap("error","Invalid Credential"));
//////		}
//////		verifiedCredential.setKey(jwtUtil.generateToken(storeCred.getUserName()));
//////		return ResponseEntity.status(200).body(Collections.singletonMap("data",verifiedCredential));
////		
////		if(storeService.getStoreCredential(storeCred) != null) {
////			System.out.println(storeService.getStoreCredential(storeCred));
//////			storeService.UpdateUser(user);
////			try {
////				authenticationManager.authenticate(
////						new UsernamePasswordAuthenticationToken(storeCred.getUserName(),storeCred.getPassword())
////						);
////			}catch(Exception e) {
////				return ResponseEntity.status(401).body(Collections.singletonMap("error","Invalid OTP"));
////			}
////			System.out.print(jwtUtil.generateToken(storeCred.getUserName()));
////			verifiedCredential.setKey(jwtUtil.generateToken(storeCred.getUserName()));
////			verifiedCredential.setNewUser(false);
////			return ResponseEntity.status(200).body(Collections.singletonMap("data",verifiedCredential));
////		}return null;
////	}
////}
