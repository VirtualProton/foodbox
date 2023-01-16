package com.foodbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.Location;

public interface LocationRepository extends JpaRepository<Location,Integer> {
	List<Location> findByLocation(String location);
}
