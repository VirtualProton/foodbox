package com.foodbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.entity.Orders;
import com.foodbox.service.OrderService;

@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/order")
@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/get_orders")
	public ResponseEntity<?> getOrderByLocation(@RequestBody Orders order){
		return orderService.findByLocation(order);
	}
	
	@PostMapping("/user_orders")
	public ResponseEntity<?> getOrderByUserName(@RequestBody Orders order){
		return orderService.findByUserName(order);
	}
	
	@PostMapping("/add_orders")
	public ResponseEntity<?> addOrder(@RequestBody Orders order){
//		System.out.println(order);
//		return null;
		return orderService.addOrder(order);
	}
}
