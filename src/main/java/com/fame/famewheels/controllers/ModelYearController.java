package com.fame.famewheels.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fame.famewheels.dto.ModelYearDto;
import com.fame.famewheels.services.ModelYearService;

@RestController
@RequestMapping("fame")
public class ModelYearController {
	
	@Autowired
	private ModelYearService modelYearService;
	
	@GetMapping("/getModelYear")
	public ResponseEntity<List<ModelYearDto>> getYear(){
		List<ModelYearDto> year =this.modelYearService.getAll();
		
		return new ResponseEntity<List<ModelYearDto>>(year, HttpStatus.OK);
	}
	
	

}
