package com.fame.famewheels.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fame.famewheels.dto.AuctionPostDto;
import com.fame.famewheels.dto.PostDto;
import com.fame.famewheels.dto.UserDto;
import com.fame.famewheels.entities.Post;
import com.fame.famewheels.entities.User;
import com.fame.famewheels.exceptions.DataIntegrityViolationException;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.services.AuctionPostService;
import com.fame.famewheels.services.PostImagesService;
import com.fame.famewheels.services.PostService;
import com.fame.famewheels.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/fame")
@Validated
public class AuctionPostController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private AuctionPostService auctionPostService;
	@Autowired
	private PostImagesService postImageService;
	
	@Autowired
	private ObjectMapper mapper;
	
    Logger logger = LoggerFactory.getLogger(AuctionPostController.class);	

	@PostMapping("/createAuctionPost")
	public ResponseEntity<?> createAuctionPost(@Valid @ModelAttribute  AuctionPostDto auctionPost, BindingResult result,
			@Valid @RequestParam String userData, @Valid @RequestParam String postData, @Valid @RequestParam MultipartFile[] imageFiles)
					throws IOException{
		
		if (result.hasErrors()) {
	        // If there are validation errors, return a bad request response with detailed error messages
	        List<FieldError> errors = result.getFieldErrors();
	        List<String> errorMessages = new ArrayList<>();
	        for (FieldError error : errors) {
	            errorMessages.add(error.getField()+error.getDefaultMessage());
	        }
	        return ResponseEntity.badRequest().body(errorMessages);
	    }
		logger.info("auctionPost {}", auctionPost);
		logger.info("user {}", userData);
		logger.info("post {}", postData);
//		List<UserDto> user= new ArrayList<>();
		
		UserDto user=null;
		try {
			user = mapper.readValue( userData, UserDto.class);
		} catch (JsonMappingException e1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user Data");
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		
		PostDto post=null;
		try {
			post=mapper.readValue(postData, PostDto.class);
		} catch (JsonMappingException e1) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid post Data");
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Check if there are more than 10 Auction Posts for today
		String date=auctionPost.getAuctionDate();
		int getPostCount=this.auctionPostService.getPostCountForToday(date);
		if(getPostCount>=10) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post Limit Exceeded for today");
		}else {
			if (result.hasErrors()) {
		        // If there are validation errors, return a bad request response with detailed error messages
		        List<FieldError> errors = result.getFieldErrors();
		        List<String> errorMessages = new ArrayList<>();
		        for (FieldError error : errors) {
		            errorMessages.add(error.getDefaultMessage());
		        }
		        return ResponseEntity.badRequest().body(errorMessages);
		    }
			
			if(user.getEmail()==null) {
				return ResponseEntity.badRequest().body("Email can not be null!!");
			}
//			User existingEmail=this.userService.findByEmail(auctionPost.getUser().getEmail());
			User existingEmail=this.userService.findByEmail(user.getEmail());

			UserDto newUser= null;
			if(existingEmail != null && existingEmail.getEmail() != null && !existingEmail.getEmail().isEmpty()){
//				result.rejectValue("email", null,
//						"Email address already exists!! Please use another email Address.");
//				newUser=this.userService.updateUserAsAuctioneer(auctionPost.getUser(), existingEmail.getId());
				newUser=this.userService.updateUserAsAuctioneer(user, existingEmail.getId());

			}
			
			if(existingEmail == null) {
				
//				newUser=this.userService.createUser(auctionPost.getUser());
				newUser=this.userService.createUser(user);

			}	
			
			
			try {
//				PostDto Post=this.postService.createPost(auctionPost.getPost());
				PostDto Post=this.postService.createPost(post);

	//
		        postImageService.uploadImages(imageFiles, Post);
		        
				auctionPostService.createAuctionPost(auctionPost, newUser, Post);
				
				return ResponseEntity.ok("form Submitted Successfully!!");
				//add values in auctionpost table
				}catch(ResourceNotFoundException e) {
					
					String errorMessage = e.getMessage();
			        return ResponseEntity.badRequest().body(errorMessage);
				}catch(DataIntegrityViolationException e) {
	        	String msg=e.getMessage();
	        	return ResponseEntity.badRequest().body(msg);
	        }
			catch (Exception e) {
		        // Handle other exceptions and return a generic error response
		        String errorMessage = "Failed to submit post. Please try again later.";
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		        }
			}
		}
		
	
	@GetMapping("/getUpcomingPost")
	public ResponseEntity<List<AuctionPostDto>> getUpcomingAuctions(@RequestParam int pageNo, @RequestParam int pageSize){
		List<AuctionPostDto> getPost= this.auctionPostService.getUpcomingPost(pageNo, pageSize);
		return new ResponseEntity<List<AuctionPostDto>>(getPost, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getCurrentPost")
	public ResponseEntity<List<AuctionPostDto>> getCurrentAuction(){
		List<AuctionPostDto> getPost= this.auctionPostService.currentAuction();
		return new ResponseEntity<List<AuctionPostDto>>(getPost, HttpStatus.OK);
	}
	
	@GetMapping("/getAuctionPostById")
	public ResponseEntity<AuctionPostDto> getAuctionPostById(@RequestParam int auctionPostId){
		AuctionPostDto getPostById =this.auctionPostService.getAuctionPostById(auctionPostId);
		return new ResponseEntity<AuctionPostDto>(getPostById, HttpStatus.OK);
	}
	
	@GetMapping("/getAuctionPostCount")
	public ResponseEntity<Map<String, Integer>> getAuctionPostCount(){
		Map<String, Integer> getCount= this.auctionPostService.getPostCount();
		return new ResponseEntity<Map<String, Integer>>(getCount, HttpStatus.OK);
	}
}
