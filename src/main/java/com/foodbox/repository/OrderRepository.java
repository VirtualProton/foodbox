package com.foodbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
	List<Orders> findByLocation(String location);
	List<Orders> findByUserName(String userName);
}
