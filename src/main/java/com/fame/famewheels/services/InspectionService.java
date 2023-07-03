package com.fame.famewheels.services;

import java.util.List;

import com.fame.famewheels.dto.VehicleInspectionDto;

public interface InspectionService {
	
	
	//create new inspections
	VehicleInspectionDto createVehicleInspection (VehicleInspectionDto vehiclInspectionDto,Integer userId);
	
	public List<VehicleInspectionDto> getInspection ();

	List<VehicleInspectionDto> getByUserId(Integer id);
}
