package com.fame.famewheels.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fame.famewheels.dto.VehicleInspectionDto;
import com.fame.famewheels.services.InspectionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/fame")
public class VehicleInspectionController {
	@Autowired
	private InspectionService inspectionService;
	
	
	//create
	@PostMapping("/users/{userId}/vehicle-inspection")
	public ResponseEntity<VehicleInspectionDto> createNewInspection(@Valid @RequestBody VehicleInspectionDto vehicleInspectionDto, @PathVariable Integer userId)
	{
		VehicleInspectionDto createVehicleInspection = this.inspectionService.createVehicleInspection(vehicleInspectionDto, userId);
		return new ResponseEntity<>(createVehicleInspection,HttpStatus.CREATED);
	}
	
	//get inspections
	@GetMapping("/getInspection")
	public ResponseEntity<List<VehicleInspectionDto>> getInspection(){
		
		List<VehicleInspectionDto> Inspection = this.inspectionService.getInspection();
		
		return new ResponseEntity<List<VehicleInspectionDto>>(Inspection, HttpStatus.OK);
	}
	
	@GetMapping("/getInspectionByUserId/{id}")
	public ResponseEntity<List<VehicleInspectionDto>> getInspectionByUserId(@PathVariable Integer id){
		List<VehicleInspectionDto> records = this.inspectionService.getByUserId(id);
		return new ResponseEntity<List<VehicleInspectionDto>>(records, HttpStatus.OK);
	}
}
