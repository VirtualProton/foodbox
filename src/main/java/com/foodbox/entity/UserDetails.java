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
@Table(name="USERDETAILS_TBL")
public class UserDetails {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(length=100)
	private String ID;
	@Column(length=100)
	private String userName;
	@Column(length=100)
	private String fullName;
	@Column(length=100)
	private String gender;
	@Column(length=100)
	private String phoneNo;
	@Column(length=100)
	private String email;
	@Column(length=100)
	private String address;
	@Column(length=100)
	private String city;
	@Column(length=100)
	private String profileImgUrl;
	
}
