package com.fame.famewheels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.fame.famewheels.entities.City;


public interface CityRepository extends JpaRepository<City, Integer> {

    City findByCityName(String cityName);

}
