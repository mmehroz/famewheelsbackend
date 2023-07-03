package com.fame.famewheels.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fame.famewheels.dto.PostTypeDto;
import com.fame.famewheels.entities.PostType;
import com.fame.famewheels.services.PostTypeService;

@RestController
public class PostTypeController {

	@Autowired
	private PostTypeService postTypeService;
	
	
	@GetMapping("/getpostType")
	public PostTypeDto getType() {
		PostTypeDto type =this.postTypeService.getPostType(1);
		return type;
	}
}
