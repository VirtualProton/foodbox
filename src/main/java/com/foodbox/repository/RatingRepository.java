package com.foodbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
	List<Rating> findByReferenceId(String referenceId);
}
