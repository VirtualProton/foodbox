package com.foodbox.entity;

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
@Table(name="FOOD_TBL")
public class FoodDetail {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(length=100)
	private String id;
	@Column(length=100)
	private String location;
	@Column(length=100)
	private String foodName;
	@Column(length=1000)
	private String discription;
	@Column(length=100)
	private String type;
	@Column(length=100)
	private String category;
	@Column(length=100)
	private boolean isAvialable;
	@Column(length=100)
	private double price;
	@Column(length=225)
	private String imgUrl;
}
