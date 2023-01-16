package com.foodbox.service;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodbox.entity.Orders;
import com.foodbox.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	
	public ResponseEntity<?> findByLocation(Orders order){
		try {
			List<Orders> orders = orderRepository.findByLocation(order.getLocation());
			if(!orders.isEmpty()) {
				return ResponseEntity.status(200).body(Collections.singletonMap("data",orders));
			}else{
				return ResponseEntity.status(404).body(Collections.singletonMap("message","no data found"));
			}
			
		}catch(Exception e) {
			return ResponseEntity.status(400).body(Collections.singletonMap("error",e.fillInStackTrace()));
		}
	}
	
	public ResponseEntity<?> findByUserName(Orders order){
		try {
			List<Orders> orders = orderRepository.findByUserName(order.getUserName());
			if(!orders.isEmpty()) {
				return ResponseEntity.status(200).body(Collections.singletonMap("data",orders));
			}else{
				return ResponseEntity.status(404).body(Collections.singletonMap("message","no data found"));
			}
			
		}catch(Exception e) {
			return ResponseEntity.status(400).body(Collections.singletonMap("error",e.fillInStackTrace()));
		}
	}
	
	public ResponseEntity<?> addOrder(Orders order){
		try {
			orderRepository.save(order);
			return ResponseEntity.status(200).body(Collections.singletonMap("message","order placed successfully"));
		}catch(Exception e) {
			return ResponseEntity.status(400).body(Collections.singletonMap("error",e.fillInStackTrace()));
		}
	}
}
