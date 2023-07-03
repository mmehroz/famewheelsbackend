package com.fame.famewheels.servicesimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.CategoryDto;
import com.fame.famewheels.entities.Category;
import com.fame.famewheels.repositories.CategoryRepository;
import com.fame.famewheels.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category saveCategory = this.categoryRepository.save(category);
		
		return this.modelMapper.map(saveCategory, CategoryDto.class);
	}



	@Override
	public List<CategoryDto> getCategories() {
		List<Category> findAll = this.categoryRepository.findAll();
		List<CategoryDto> categories = findAll.stream().map((get)-> this.modelMapper.map(get, CategoryDto.class)).collect(Collectors.toList());
		return categories;
	}



	@Override
	public CategoryDto getBycategoryId(Integer id) {
//		List<Post> makes = this.postRepository.findByMake(make);
//		List<PostDto> postByMake = makes.stream().map((mak)->this.modelMapper.map(mak, PostDto.class)).collect(Collectors.toList());
//		return postByMake;
		Category category =this.categoryRepository.getBycategoryId(id);
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> categoryFilters(String title) {
		List<Category> findAll = this.categoryRepository.findBytitle(title);
		List<CategoryDto> categories = findAll.stream().map((get)-> this.modelMapper.map(get, CategoryDto.class)).collect(Collectors.toList());
		
		return categories;
	}
	
//	@Override
//	public List<CategoryDto> categoryFilters(String title){
//		return categoryRepository.getCategoryfilters(title);
//	}
	
	

}
