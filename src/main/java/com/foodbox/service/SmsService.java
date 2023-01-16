package com.foodbox.service;

import java.text.ParseException;

import org.springframework.stereotype.Component;

import com.foodbox.dto.SmsDto;
import com.foodbox.dto.StoreOTP;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



@Component
public class SmsService {
	private final String ACCOUNT_SID="ACc599060b206ec2a035eb2457c797f785";
	private final String AUTH_TOKEN="176a13a7df6449cb3d10705b7059e224";
	private final String FROM_NUMBER="+12182748477";
	
	public void send(SmsDto sms) throws ParseException{
		 
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		
		int min = 100000;
		int max = 999999;
		int number =(int)(Math.random()*(max-min+1)+min);
		
		String msg = "Use verification code "+number+" for FoodBox authentication";
		
		Message message = Message.creator(new PhoneNumber(sms.getPhoneNo()),new PhoneNumber(FROM_NUMBER),msg)
				.create();
		StoreOTP.setOtp(String.valueOf(number));
	}
}
