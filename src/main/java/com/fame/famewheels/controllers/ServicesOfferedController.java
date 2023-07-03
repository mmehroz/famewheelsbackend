package com.fame.famewheels.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fame.famewheels.dto.ServicesOfferedDto;
import com.fame.famewheels.services.ServicesOfferedService;

@RestController
@RequestMapping("/fame")
public class ServicesOfferedController {
	@Autowired
	private ServicesOfferedService offeredService;
	
	@PostMapping("/UserServices/{userId}/Services/{vehicleServiceAssistantId}")
	public ResponseEntity<ServicesOfferedDto> createServiceOffer(
	
			@RequestBody ServicesOfferedDto servicesOfferedDto,
			@PathVariable Integer userId,
			@PathVariable Integer vehicleServiceAssistantId)
	
	{
		ServicesOfferedDto createService = this.offeredService.createService(servicesOfferedDto, userId, vehicleServiceAssistantId);
		return new ResponseEntity<ServicesOfferedDto>(createService,HttpStatus.CREATED);
	}
}
