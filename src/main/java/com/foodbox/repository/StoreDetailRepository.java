package com.foodbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.StoreDetails;

public interface StoreDetailRepository extends JpaRepository<StoreDetails, Integer> {
	List<StoreDetails> findByUserName(String userName);
}