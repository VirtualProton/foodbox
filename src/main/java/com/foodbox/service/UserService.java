package com.foodbox.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.foodbox.entity.User;
import com.foodbox.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username){
		// TODO Auto-generated method stub
		try {
			User user=repository.findByUserName(username);
//			System.out.println(user);
			return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(), new ArrayList<>());
		}catch(UsernameNotFoundException e) {
//			System.out.println("user not found");
			return null;
		}	
	}
	
	public User getUserCredential(User user) {
		User user1 = repository.findByUserName(user.getUserName());
		return user1;
	}
	public Boolean addUser(User user) {
//		System.out.print(user);
		try {
			repository.save(user);
			return true;
		}catch(Exception e) {
			return false;
		}	
	}
	
	public User UpdateUser(User user) {
		User existingDetails = repository.findByUserName(user.getUserName());
		existingDetails.setPassword(user.getPassword());
		return repository.save(existingDetails);
	}
}
