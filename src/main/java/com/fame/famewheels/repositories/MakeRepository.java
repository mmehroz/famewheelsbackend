package com.fame.famewheels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fame.famewheels.entities.Make;

public interface MakeRepository extends JpaRepository<Make, Integer> {

	Make findByMakeName(String makeName);
    
//	Make findBymake(String make);
}
