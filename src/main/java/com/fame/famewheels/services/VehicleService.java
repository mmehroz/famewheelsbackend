package com.fame.famewheels.services;

import java.util.List;

import com.fame.famewheels.dto.VehicleDto;

public interface VehicleService {
	
	//create Vehicle
    VehicleDto createPostVehicle(VehicleDto vehicleDto, Integer userId, Integer categoryId);

    //update vehicle
    VehicleDto updateVehicle (VehicleDto vehicleDto , Integer vehicleId);
	
	//delete vehicle
	void deletePost(Integer vehicleId);
	
	//get vehicle
	VehicleDto getVehicleById(Integer vehicleId);
	
	//get all vehicles
	List<VehicleDto> getAllVehicles ();
	
	//get all vehicles by category
	List<VehicleDto> getVehiclesByCategoryId (Integer categoryId);
	
	//get all vehicles by user
	List<VehicleDto> getVehiclesByUser(Integer userId);
	
	//search posts
	List<VehicleDto> searchVehicles(String keyword);

}
