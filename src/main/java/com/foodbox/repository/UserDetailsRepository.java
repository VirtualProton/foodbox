package com.foodbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
	List<UserDetails> findByUserName(String userName);
}
