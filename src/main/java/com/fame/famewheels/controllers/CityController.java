package com.fame.famewheels.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fame.famewheels.dto.CityDto;
import com.fame.famewheels.services.CityService;




@RestController
@RequestMapping("fame")
public class CityController {
	@Autowired
	private CityService cityService;

	@GetMapping("/cities")
	public ResponseEntity<List<CityDto>> getAllcities(){
		List<CityDto> cities = this.cityService.getCities();
		
		return new ResponseEntity<List<CityDto>>(cities, HttpStatus.OK);
	}
}
