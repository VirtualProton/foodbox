package com.foodbox.service;

import org.springframework.stereotype.Service;

import com.foodbox.entity.StoreCredential;
import com.foodbox.repository.StoreRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class StoreService  {
	
	@Autowired
	private StoreRepository storeRepository;

//	@Override
//	public UserDetails loadUserByUsername(String username){
//		// TODO Auto-generated method stub
//		
//		try {
//			StoreCredential storeCred = storeRepository.findByUserName(username);
//			return new org.springframework.security.core.userdetails.User(storeCred.getUserName(),storeCred.getPassword(),new ArrayList<>());
//		}catch(UsernameNotFoundException e) {
//			return null;
//		}
//	}
	
	public StoreCredential getStoreCredential(StoreCredential storeCred) {
		StoreCredential storeCred1 = storeRepository.findByUserName(storeCred.getUserName());
		return storeCred1;
	}
	
	public StoreCredential addStore(StoreCredential storeCred) {
		try {
			StoreCredential existingStoreCred = storeRepository.findByUserName(storeCred.getUserName());
			if(existingStoreCred == null) {
				return storeRepository.save(storeCred);
			}
		}catch(Exception e) {
			System.out.print(e.fillInStackTrace());
			return null;
		}
		return null;
	}
	
	public StoreCredential updateStoreCred(StoreCredential storeCred){
		
		StoreCredential existingStoreCred = storeRepository.findByUserName(storeCred.getUserName());
		existingStoreCred.setPassword(storeCred.getPassword());
		
		return storeRepository.save(existingStoreCred);
	}
}
