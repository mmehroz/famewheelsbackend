package com.fame.famewheels.services;

import java.util.List;

import com.fame.famewheels.dto.CategoryDto;

public interface CategoryService {
	
	//create
		CategoryDto createCategory(CategoryDto categoryDto);
		
	
		
		List<CategoryDto> getCategories();



		CategoryDto getBycategoryId(Integer id);
		
		List<CategoryDto> categoryFilters(String title);

}
