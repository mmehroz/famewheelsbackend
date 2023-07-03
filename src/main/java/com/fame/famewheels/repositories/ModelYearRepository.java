package com.fame.famewheels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fame.famewheels.entities.ModelYear;

public interface ModelYearRepository extends JpaRepository<ModelYear, Integer>{

	ModelYear findByYearName(String modelYearName);
	
	

}
