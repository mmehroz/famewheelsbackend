package com.fame.famewheels.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fame.famewheels.entities.User;
import com.fame.famewheels.entities.VehicleInspection;

public interface VehicleInspectionRepository extends JpaRepository<VehicleInspection, Integer> {
	
	List<VehicleInspection> getByUser (User user);

}
