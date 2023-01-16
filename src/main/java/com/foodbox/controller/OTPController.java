package com.foodbox.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.dto.SmsDto;
import com.foodbox.dto.TempOTP;
import com.foodbox.entity.AuthRequest;
import com.foodbox.entity.User;
import com.foodbox.service.AuthenticateService;
import com.foodbox.service.SmsService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/otp")
public class OTPController {
	
	@Autowired
	SmsService smsservice;
	
	@Autowired
	AuthenticateService authenticateService;
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
	//public AuthRequest authRequest = new AuthRequest();
	private final String TOPIC_DESTINATION = "/lesson/sms";
	
	@PostMapping("/send")
	public ResponseEntity<?> sendOtp(@RequestBody SmsDto sms){
		try {
			smsservice.send(sms);
			//authRequest.setPhoneNo(sms.getPhoneNo());
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		webSocket.convertAndSend(TOPIC_DESTINATION,getTimeStamp()+": SMS has been sent!");
		return ResponseEntity.status(200).body(Collections.singletonMap("message","SMS has been sent!: "+sms.getPhoneNo()));
	}
	
	@PostMapping("/verify")
	public ResponseEntity<?> verifyOtp(@RequestBody User user) throws Throwable{
//		System.out.println(userInput.getOtp());
//		authRequest.setOtp(userInput.getOtp());
		System.out.println(user);
		
		return authenticateService.otpAuthenticate(user);
	}

	private String getTimeStamp() {
		// TODO Auto-generated method stub
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	}
}
