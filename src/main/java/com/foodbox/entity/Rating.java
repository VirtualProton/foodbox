package com.foodbox.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="RATING_TBL")
public class Rating {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(length=200)
	private String id;
	@Column(length=200,nullable= false)
	private String referenceId;
	@Column(length=200, nullable= false)
	private int rating;
}
