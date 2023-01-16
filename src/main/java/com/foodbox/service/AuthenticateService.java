package com.foodbox.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.foodbox.dto.StoreOTP;
import com.foodbox.dto.VerifiedCredential;
import com.foodbox.entity.User;
import com.foodbox.util.JwtUtil;

@Service
public class AuthenticateService {
	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService; 
	VerifiedCredential verifiedCredential = new VerifiedCredential();
	public ResponseEntity<?> otpAuthenticate(User user) throws Throwable {
		System.out.print(user);
		if(user.getPassword().equalsIgnoreCase(StoreOTP.getOtp())) {
			if(userService.getUserCredential(user) != null) {
				System.out.println(userService.getUserCredential(user));
				userService.UpdateUser(user);
				try {
					authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword())
							);
				}catch(Exception e) {
					return ResponseEntity.status(401).body(Collections.singletonMap("error","Invalid OTP"));
				}
				System.out.print(jwtUtil.generateToken(user.getUserName()));
				verifiedCredential.setKey(jwtUtil.generateToken(user.getUserName()));
				verifiedCredential.setNewUser(false);
				return ResponseEntity.status(200).body(Collections.singletonMap("data",verifiedCredential));
			}else {
				boolean isSuccess = false;
				System.out.print("user");
				isSuccess=userService.addUser(user);
				if(isSuccess) {
					try {
						authenticationManager.authenticate(
								new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword())
								);
					}catch(Exception e) {
						throw new Exception("Invalid username or password");
					}
					verifiedCredential.setKey(jwtUtil.generateToken(user.getUserName()));
					verifiedCredential.setNewUser(true);
					return ResponseEntity.status(200).body(Collections.singletonMap("data",verifiedCredential));
				}
			}
		}
		return ResponseEntity.status(403).body(Collections.singletonMap("error","Incorrect Otp"));	
	}
}
