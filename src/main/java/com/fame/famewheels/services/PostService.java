package com.fame.famewheels.services;

import java.util.List;

import com.fame.famewheels.dto.PostDto;
import com.fame.famewheels.entities.City;

public interface PostService {
	
	    //create post
	    PostDto createPost(PostDto postDto);
	
	    //update
		PostDto updatePost (PostDto postDto , Integer postId);
		
		//delete post
		void deletePost(Integer postId);
		
		//get post
		PostDto getPostById(Integer postId);
		
		//get all post
		List<PostDto> getAllPosts (Integer pageNo, Integer pageSize);
		
		//get all posts by category Id
		List<PostDto> getPostsByCategoryId (Integer categoryId);
		
//		//get all posts by category Name
		List<PostDto> getPostsByCategoryName(String categoryName);
		
		//get all posts by user
		List<PostDto> getPostsByUserId(Integer userId);
		
		//get all posts by city
		// List<PostDto> getPostsByCity(String city);
		
		//get all posts by make
		List<PostDto> getPostsByMake(String make);
		
		//get all posts by model
		List<PostDto> getPostsByModel(String model);
		
		//add post cover in postId
		String postCover(Integer postId, String coverImageUrl);
		
		//search posts
		List<PostDto> searchPosts(String keyword);
		
		//Vehicle Condition 
		List<PostDto> vehicleCondition(String condition, Integer pageNo, Integer pageSize);
		
		//vehicle Type
		List<PostDto> vehicleType(Integer typeId, Integer pageNo, Integer pageSize);
		
		//Post filters 
		List<PostDto> filters(String vehicleCondition, Integer city, Integer make, Integer minPrice, Integer maxPrice,
				Integer category, String keyword, Integer PageNo, Integer pageSize);

}
