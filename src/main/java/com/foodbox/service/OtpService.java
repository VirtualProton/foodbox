package com.foodbox.service;

import java.text.DecimalFormat;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class OtpService {
	
	public String generateOtp() {
		return new DecimalFormat("000000")
				.format(new Random().nextInt(999999));
	}
}
