package com.foodbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodbox.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUserName(String userName);
}
