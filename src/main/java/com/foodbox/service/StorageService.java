package com.foodbox.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class StorageService {
	
//	@Value("${application.bucket.name}")
	private String bucketName = "foodboxstorage";
	
	@Autowired
	private AmazonS3 s3Client;
	
	public ResponseEntity<?>  uploadFile(MultipartFile file){
		File fileObj = convertMultipartFileToFile(file);
		String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
		s3Client.putObject(new PutObjectRequest(bucketName,fileName,fileObj));
		fileObj.delete();
		return ResponseEntity.status(200).body(Collections.singletonMap("url", s3Client.getUrl(bucketName, fileName)));

	}
	
	private File convertMultipartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try(FileOutputStream fos = new FileOutputStream(convertedFile)){
			fos.write(file.getBytes());
		}catch(IOException e) {
			System.out.println(e.getStackTrace());
		}
		return convertedFile;
	}
}
