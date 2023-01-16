package com.foodbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.entity.AuthRequest;
import com.foodbox.service.OtpService;
import com.foodbox.service.UserService;
import com.foodbox.util.JwtUtil;



@RestController
public class HomeController {
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private UserService userService;
	
	protected String otp= null;
	
	@GetMapping("/")
	public String welcome() {
		System.out.println(otpService.generateOtp());
		//System.out.println(customUserDetailsService.loadUserByUsername("pritam"));
		return "Welcome Bitch";	
	}
	
//	@PostMapping("/authenticate")
//	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{
//		System.out.println(otp);
//		System.out.println(authRequest.getOtp());
//		if(authRequest.getOtp().equalsIgnoreCase(otp)) {
//			if(userService.loadUserByUsername(authRequest.getPhoneNo()) != null) {
//				userService.UpdateUser(authRequest);
//				try {
//					authenticationManager.authenticate(
//							new UsernamePasswordAuthenticationToken(authRequest.getPhoneNo(),authRequest.getOtp())
//							);
//				}catch(Exception e) {
//					throw new Exception("Invalid username or password");
//				}
//				System.out.print(jwtUtil.generateToken(authRequest.getPhoneNo()));
//				return jwtUtil.generateToken(authRequest.getPhoneNo());
//			}else {
//				return "No user found";
//			}
//		}
//		return "Incorrect Otp";	
//	}
//	
//	@GetMapping("/otp")
//	public String getOtp() {
//		otp = otpService.generateOtp();
//		return otp;
//	}
}
