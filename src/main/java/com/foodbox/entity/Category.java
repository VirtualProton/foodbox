package com.foodbox.entity;

import org.hibernate.annotations.GenericGenerator;

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
@Table(name="CATEGORY_TBL")
public class Category {
		@Id
		@GeneratedValue(generator="system-uuid")
		@GenericGenerator(name="system-uuid", strategy = "uuid")
		private String id;
		private String location;
		private String category;
		private	String imgurl;
}
