package com.fame.famewheels.services;

import java.util.List;

import com.fame.famewheels.dto.VehicleServiceAssistantDto;

public interface VehicleServiceAssistantService {
	//create Service Assistant
	VehicleServiceAssistantDto createService (VehicleServiceAssistantDto assistantDto);
	
	//Get all services assistant
	List<VehicleServiceAssistantDto> getAllServices ();

}
