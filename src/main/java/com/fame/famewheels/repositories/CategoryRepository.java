package com.fame.famewheels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

//import com.fame.famewheels.dto.CategoryDto;
import com.fame.famewheels.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
//	Category findById(Integer categoryId);
	Category findByCategoryName(String categoryName);
	
	
	@Query(value="SELECT * from category c where c.title= ?1", nativeQuery = true)
	public List<Category> findBytitle( String title);

	public Category getBycategoryId(Integer id);

	

}
