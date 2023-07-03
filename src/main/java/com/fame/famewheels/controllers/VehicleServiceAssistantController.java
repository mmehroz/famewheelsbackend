package com.fame.famewheels.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fame.famewheels.dto.VehicleServiceAssistantDto;
import com.fame.famewheels.services.VehicleServiceAssistantService;

@RestController
@RequestMapping("/fame")
public class VehicleServiceAssistantController {
	
	@Autowired
	private VehicleServiceAssistantService assistantService;
	
	@PostMapping("/AddVehicleAssistant")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<VehicleServiceAssistantDto> createVehicleAssistant(@RequestBody VehicleServiceAssistantDto assistantDto)
	{
		VehicleServiceAssistantDto createService = this.assistantService.createService(assistantDto);
		return new ResponseEntity<VehicleServiceAssistantDto>(createService,HttpStatus.CREATED);
	}
	
	@GetMapping("/GetAllVehicleAssistant")
	public ResponseEntity<List<VehicleServiceAssistantDto>> getAllVehicleAssistant()
	{
		List<VehicleServiceAssistantDto> allServices = this.assistantService.getAllServices();
		return new ResponseEntity<List<VehicleServiceAssistantDto>>(allServices,HttpStatus.OK);
	}
}
