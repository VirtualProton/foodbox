package com.foodbox.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class StorageConfig {
	
//	@Value("${cloud.aws.credentials.access-key}")
	private String accessKey = "AKIAZYDKTJ7N7IN6RK5O";
	
//	@Value("${cloud.aws.credentials.secret-key}")
	private String accessSecret ="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";
	
//	@Value("${cloud.aws.region.static}")
	private String region = "ap-south-1";
	
	@Bean
	public AmazonS3 generateS3client() {
	 AWSCredentials credentails	= new BasicAWSCredentials(accessKey,accessSecret);
	 return AmazonS3ClientBuilder.standard()
			 .withCredentials(new AWSStaticCredentialsProvider(credentails))
	 			.withRegion(region).build();
	 
	}
}
