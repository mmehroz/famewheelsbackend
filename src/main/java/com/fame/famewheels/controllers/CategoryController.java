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

import com.fame.famewheels.dto.CategoryDto;
//import com.fame.famewheels.entities.Category;
import com.fame.famewheels.services.CategoryService;

@RestController
@RequestMapping("fame/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/addCategory")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
	{
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}	
	
	
	//get All categories
	@GetMapping("/getAll")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		List<CategoryDto> categories = this.categoryService.getCategories();
		
		return new ResponseEntity<List<CategoryDto>>(categories,HttpStatus.OK);
	}
	
	@GetMapping("/filters/{title}")
	public ResponseEntity<List<CategoryDto>> CategoryFilters(@PathVariable String title)
	{
		List<CategoryDto> category = this.categoryService.categoryFilters(title);
		
		return new ResponseEntity<List<CategoryDto>>(category,HttpStatus.OK);
	}
	
	@GetMapping("getById/{id}")
	public ResponseEntity<CategoryDto> getBycategoryId(@PathVariable Integer id){
		CategoryDto category = this.categoryService.getBycategoryId(id);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
	}
}
