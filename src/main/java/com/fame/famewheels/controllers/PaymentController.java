package com.fame.famewheels.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fame.famewheels.dto.PaymentDto;
import com.fame.famewheels.dto.UserDto;
import com.fame.famewheels.entities.Payment;
import com.fame.famewheels.entities.User;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.repositories.UserRepository;
import com.fame.famewheels.services.PaymentService;
import com.fame.famewheels.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/fame")
public class PaymentController {


	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserService userService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping("/createMember")
	public ResponseEntity<?> createMember(@Valid @ModelAttribute PaymentDto payment, BindingResult result, @Valid @RequestParam String userData){
		
		UserDto user=null;
		try {
			user = mapper.readValue(userData, UserDto.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if(user.getEmail()==null) {
			return ResponseEntity.badRequest().body("Email can not be null!!");
		}
		
		User getUser= this.userService.findByEmail(user.getEmail());
		
		UserDto newUser=null;
		if( getUser != null && getUser.getEmail() != null && !getUser.getEmail().isEmpty()) {
			newUser=this.userService.updateUserAsBidder(user, getUser.getId());
		}
		if(getUser == null ) {
			newUser =this.userService.createUser(user);
		}
		
		if(result.hasErrors()) {
			List<FieldError> errors=result.getFieldErrors();
			List<String> errorMsg=new ArrayList<>();
			for(FieldError error: errors) {
				errorMsg.add(error.getField()+ error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(errorMsg);
		}
			
		try {
			this.paymentService.createMember(payment, newUser.getId());
			return ResponseEntity.ok("Form Submitted Successfully!!");
		}catch(ResourceNotFoundException e) {
			String errMsg= e.getMessage();
			return ResponseEntity.badRequest().body(errMsg);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	
	}
	
}
