package com.fame.famewheels.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fame.famewheels.entities.Make;
import com.fame.famewheels.entities.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {
	
	List<Model> getByMake(Make make);

	Model findByModelName(String modelName);

}
