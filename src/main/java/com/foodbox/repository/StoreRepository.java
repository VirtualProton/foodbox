package com.foodbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodbox.entity.StoreCredential;


public interface StoreRepository extends JpaRepository<StoreCredential, Integer> {
	StoreCredential findByUserName(String userName);
}
