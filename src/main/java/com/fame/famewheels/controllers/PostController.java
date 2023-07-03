package com.fame.famewheels.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import com.fame.famewheels.dto.ApiResponse;
import com.fame.famewheels.dto.PostDto;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.services.PostImagesService;
import com.fame.famewheels.services.PostService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/fame")
public class PostController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostImagesService postImagesService;
	
	
	
	

	@Value("${project.image}")
	private String path;

//	 create
	@PostMapping("/create/post")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
		PostDto createPost = this.postService.createPost(postDto);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

	}
	
//	@PostMapping("/create/post")
//	public ResponseEntity<String> createPost(@RequestBody PostDto postDto) {
//		this.postService.createPost(postDto);
//		return ResponseEntity.ok("Successfully Uploaded Data");
//	}
	
//	@PostMapping("/submit")
//	public ResponseEntity<String> submitpost(@ModelAttribute PostDto postDto, @RequestParam MultipartFile[] imageFiles )
//	{
//		PostDto createPost = this.postService.createPost(postDto);
//		createPost.getPostId();
//		this.postImagesService.uploadImages(imageFiles, createPost);
//		return ResponseEntity.ok("Form Submitted Successfully");
//	}
	
	@PostMapping("/submit")
	public ResponseEntity<?> submitPost(@Valid @ModelAttribute PostDto postDto, BindingResult bindingResult, 
			@RequestParam MultipartFile[] imageFiles) {
	    if (bindingResult.hasErrors()) {
	        // If there are validation errors, return a bad request response with detailed error messages
	        List<FieldError> errors = bindingResult.getFieldErrors();
	        List<String> errorMessages = new ArrayList<>();
	        for (FieldError error : errors) {
	            errorMessages.add(error.getField()+" "+error.getDefaultMessage());
	        }
	        return ResponseEntity.badRequest().body(errorMessages);
	    }

	    try {
	        PostDto createdPost = postService.createPost(postDto);
	        postImagesService.uploadImages(imageFiles, createdPost);
	        return ResponseEntity.ok("Form Submitted Successfully");
	    } catch (ResourceNotFoundException e) {
	        // Handle the exception and return an error response
	        String errorMessage = e.getMessage();
	        return ResponseEntity.badRequest().body(errorMessage);
	    } catch (Exception e) {
	        // Handle other exceptions and return a generic error response
	        String errorMessage = "Failed to submit post. Please try again later.";
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    }
	}
	// update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	
	
	@PutMapping("/updatedPost/{postId}")
	public ResponseEntity<?> updatePost(@Valid @ModelAttribute PostDto postDto, BindingResult bindingResult, @RequestParam MultipartFile[] imageFiles, @PathVariable Integer postId) {
	    if (bindingResult.hasErrors()) {
	        // If there are validation errors, return a bad request response with detailed error messages
	        List<FieldError> errors = bindingResult.getFieldErrors();
	        List<String> errorMessages = new ArrayList<>();
	        for (FieldError error : errors) {
	            errorMessages.add(error.getDefaultMessage());
	        }
	        return ResponseEntity.badRequest().body(errorMessages);
	    }
	    

	    try {
	        PostDto updatedPost = postService.updatePost(postDto, postId);
	        postImagesService.updateImages(imageFiles, updatedPost);
	        return ResponseEntity.ok("Form Submitted Successfully");
	    } catch (ResourceNotFoundException e) {
	        // Handle the exception and return an error response
	        String errorMessage = e.getMessage();
	        return ResponseEntity.badRequest().body(errorMessage);
	    } catch (Exception e) {
	        // Handle other exceptions and return a generic error response
	        String errorMessage = "Failed to submit post. Please try again later.";
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    }
	}

	
	
	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostDto> update(@RequestBody PostDto postDto, @PathVariable Integer postId){
		return null;
	}

	// delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("post is successfully deleted !!", true);
	}
	
	//get post by post id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postId)
	{
		
		PostDto postDto = this.postService.getPostById(postId);
		postDto.setCounter(postDto.getCounter()+1);
		this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	//get all posts
	@GetMapping("/posts/{pageNo}/{pageSize}")
	public ResponseEntity<List<PostDto>> getAllPosts(@PathVariable(required=false) Integer pageNo, @PathVariable(required=false) Integer pageSize)
	{
		List<PostDto> allPosts = this.postService.getAllPosts(pageNo, pageSize);
		return new ResponseEntity<List<PostDto>>(allPosts,HttpStatus.OK);
	}
	
	//get posts by category id
	@GetMapping("/posts/category-posts/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostsByCategoryId(@PathVariable Integer categoryId)
	{
		List<PostDto> postsByCategoryId = this.postService.getPostsByCategoryId(categoryId);
		return new ResponseEntity<List<PostDto>>(postsByCategoryId,HttpStatus.OK);
	}
	
	//get posts by category name
	@GetMapping("/posts/category-posts")
	public ResponseEntity<List<PostDto>> getPostsByCategoryName(@RequestParam String categoryName)
	{
		List<PostDto> postsByCategoryName = this.postService.getPostsByCategoryName(categoryName);
		return new ResponseEntity<List<PostDto>>(postsByCategoryName,HttpStatus.OK);
	}
	
	//get posts by user id
	@GetMapping("/posts/user-posts/{userId}")
	public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Integer userId)
	{
		List<PostDto> postsByUserId = this.postService.getPostsByUserId(userId);
		return new ResponseEntity<List<PostDto>>(postsByUserId,HttpStatus.OK);
	}
	
	// //get posts by city name
	// @GetMapping("/posts/city-posts/{city}")
	// public ResponseEntity<List<PostDto>> getPostsByCity(@PathVariable String city)
	// {
	// 	List<PostDto> postsByCity = this.postService.getPostsByCity(city);
	// 	return new ResponseEntity<List<PostDto>>(postsByCity,HttpStatus.OK);
	// }
	
	//get posts by vehicle make
	@GetMapping("/posts/make-posts/{make}")
	public ResponseEntity<List<PostDto>> getPostsByMake(@PathVariable String make)
	{
		List<PostDto> postsByMake = this.postService.getPostsByMake(make);
		return new ResponseEntity<List<PostDto>>(postsByMake,HttpStatus.OK);
	}
	
	//get Posts by model
	@GetMapping("/posts/model-posts/{model}")
	public ResponseEntity<List<PostDto>> getPostsByModel(@PathVariable String model)
	{
		List<PostDto> postsByModel = this.postService.getPostsByModel(model);
		return new ResponseEntity<List<PostDto>>(postsByModel,HttpStatus.OK);
	}
	
	/*
	 * 
	 * 
	 * the below code (controller) belongs to image service to upload images of cars
	 * in a free ad post feature of fame wheels
	 * 
	 * 
	 * 
	 */

	// upload image to posts
//	@PostMapping("/posts/image/upload/{postId}")
//	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId)
//			throws IOException {
//
//		PostDto postDto = this.postService.getPostById(postId);
//		String fileName = this.fileService.uploadImage(path, image);
//		System.out.println(fileName);
//
//		postDto.setVehicleMainImage(fileName);
//
//		PostDto updatedPost = this.postService.updatePost(postDto, postId);
//		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
//	}

	// download image

//	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
//	public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
//
//		InputStream resource = this.fileService.getResource(path, imageName);
//		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//		StreamUtils.copy(resource, response.getOutputStream());
//	}
	
	
	//get by Vehicle condition
	@GetMapping("/posts/condition/{condition}/{pageNo}/{pageSize}")
	public ResponseEntity<List<PostDto>> vehicleCondition(@PathVariable String condition, @PathVariable Integer pageNo,
			@PathVariable Integer pageSize){
		List<PostDto> cond = this.postService.vehicleCondition(condition, pageNo, pageSize);
		return new ResponseEntity<List<PostDto>>(cond, HttpStatus.OK);
	}
	
	//get By Vehicle Type
	@GetMapping("/posts/type")
	public ResponseEntity<List<PostDto>> vehicleType(@RequestParam Integer typeId, @RequestParam Integer pageNo, @RequestParam Integer pageSize){
		List<PostDto> post = this.postService.vehicleType(typeId, pageNo, pageSize);
		return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);	
	}
	
	
	@GetMapping("/posts/filters/{vehicleCondition}/{city}/{make}/{minPrice}/{maxPrice}/{category}/{search}/{pageNo}/{pageSize}")
	public ResponseEntity<List<PostDto>> filters(@PathVariable(required=false) String vehicleCondition, @PathVariable(required = false) Integer city , @PathVariable(required = false) Integer make, @PathVariable(required = false) Integer minPrice, @PathVariable(required = false) Integer maxPrice, @PathVariable(required=false) Integer category,
			@PathVariable(required=false) String search ,@PathVariable(required=false) Integer pageNo, @PathVariable(required=false) Integer pageSize
			){
		
		List<PostDto> filter = this.postService.filters(vehicleCondition, city, make, minPrice, maxPrice, category, search, pageNo, pageSize);
		return new ResponseEntity<List<PostDto>>(filter, HttpStatus.OK);
	}
	
	
	@GetMapping("/searchpost/{searchValue}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable(required=false) String searchValue){
		List<PostDto> search=this.postService.searchPosts(searchValue);
		return new ResponseEntity<List<PostDto>>(search, HttpStatus.OK);
	}
}
