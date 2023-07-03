package com.fame.famewheels.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fame.famewheels.dto.ApiResponse;
import com.fame.famewheels.dto.VehicleDto;
import com.fame.famewheels.services.VehicleService;

@RestController
@RequestMapping("/fame")
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;
	
	
	//create vehicle
	@PostMapping("/users/{userId}/categories/{categoryId}/vehicles")
	public ResponseEntity<VehicleDto> createVehiclePost(
			@RequestBody VehicleDto vehicleDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			)
	{
		VehicleDto createVehicle = this.vehicleService.createPostVehicle(vehicleDto, userId, categoryId);
		return new ResponseEntity<VehicleDto>(createVehicle,HttpStatus.CREATED);
	}
	
	
	//updateVehicle
	@PutMapping("/{vehicleId}/vehicles")
	public ResponseEntity<VehicleDto> updateVehicle(
			@RequestBody VehicleDto vehcileDto,
			@PathVariable Integer vehicleId)
	{
		VehicleDto updatedVehicle = this.vehicleService.updateVehicle(vehcileDto, vehicleId);
		return new ResponseEntity<VehicleDto>(updatedVehicle,HttpStatus.OK);
	}
	
	//deleteVehicle
	@DeleteMapping("/vehicles/{vehicleId}")
	public ResponseEntity<ApiResponse> deleteVehicle(@PathVariable Integer vehicleId)
	{
		this.vehicleService.deletePost(vehicleId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Vehicle Successfully", true ), HttpStatus.OK);
	}
	
	//get Vehicle by vehicle Id
	@GetMapping("/vehicles/{vehicleId}")
	public ResponseEntity<VehicleDto> getVehicleByVehicleId(@PathVariable Integer vehicleId)
	{
		VehicleDto vehicle = this.vehicleService.getVehicleById(vehicleId);
		return new ResponseEntity<VehicleDto>(vehicle,HttpStatus.OK);
	}
	
	//getAllVehicles
	@GetMapping("/vehicles")
	public ResponseEntity<List<VehicleDto>> getAllVehicles()
	{
		List<VehicleDto> allVehicles = this.vehicleService.getAllVehicles();
		return new ResponseEntity<List<VehicleDto>>(allVehicles,HttpStatus.OK);
	}
	
	//get vehicles by user Id
	@GetMapping("/{userId}/vehicles")
	public ResponseEntity<List<VehicleDto>> getVehicleByUserId(@PathVariable Integer userId)
	{
		List<VehicleDto> vehiclesByUser = this.vehicleService.getVehiclesByUser(userId);
		return new ResponseEntity<List<VehicleDto>>(vehiclesByUser,HttpStatus.OK);
	}
	
	//get vehicles by category Id
	@GetMapping("/{categoryId}/categories/vehicles")
	public ResponseEntity<List<VehicleDto>> getVehicleByCategoryId(@PathVariable Integer categoryId)
	{
		List<VehicleDto> vehiclesByCategoryId = this.vehicleService.getVehiclesByCategoryId(categoryId);
		return new ResponseEntity<List<VehicleDto>>(vehiclesByCategoryId,HttpStatus.OK);
	}
	
}
