package com.fame.famewheels.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fame.famewheels.dto.ModelDto;
import com.fame.famewheels.services.ModelService;

@RestController
@RequestMapping("fame")
public class ModelController {
	
	@Autowired
	private ModelService modelService;
	
	
	@GetMapping("/getModel")
	public ResponseEntity<List<ModelDto>> getmodel(){
		List<ModelDto> model= this.modelService.getAllModel();
		return new ResponseEntity<List<ModelDto>>(model, HttpStatus.OK);
	}
	
	@GetMapping("/getByMakeId")
	public ResponseEntity<List<ModelDto>> getByMakeId(@RequestParam Integer makeId){
		List<ModelDto> model =this.modelService.getByMakeId(makeId);
		return new ResponseEntity<List<ModelDto>>(model, HttpStatus.OK);
	}

}
