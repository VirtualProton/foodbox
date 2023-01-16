package com.foodbox.entity;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ORDERS_TBL")
public class Orders {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(length=100)
	private String order_id;
	@Column(length=100)
	private String location;	
	@Column(length=225)
	private String orderedItem;
	@Column(length=100)
	private String userName;
	@Column(length=100)
	private String contact;
	@Column(length=100)
	private String email;
	@Column(length=100)
	private String customerName;
	@Column(length=100)
	private String fullAddress;
//	@Column(length=500)
//	private String status ;
	@Column(length=100)
	private double totalPrice;
	@Column(length=100)
	private String transcId;
	@Column(length=100)
	private Date ordered_on = new Date();
}
