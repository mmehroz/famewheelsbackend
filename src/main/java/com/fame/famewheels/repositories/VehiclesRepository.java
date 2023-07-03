package com.fame.famewheels.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fame.famewheels.entities.Category;
import com.fame.famewheels.entities.User;
import com.fame.famewheels.entities.Vehicles;

public interface VehiclesRepository extends JpaRepository<Vehicles, Integer> {
	
	List<Vehicles> findByUser (User user);
	List<Vehicles> findByCategory(Category category);

}
