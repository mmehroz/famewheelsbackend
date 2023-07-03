package com.fame.famewheels.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.fame.famewheels.config.CustomUserDetailService;
import com.fame.famewheels.dto.ApiResponse;
import com.fame.famewheels.dto.UserDto;
import com.fame.famewheels.entities.Role;
import com.fame.famewheels.entities.User;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.repositories.UserRepository;
import com.fame.famewheels.services.JwtService;
import com.fame.famewheels.services.UserService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/fame")
@CrossOrigin(origins = "http://192.168.18.246:3000")
@Validated
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private CustomUserDetailService customUserDetailService;

		
	//creating user
	
	@PostMapping("/users/new")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto, BindingResult result)
	{
		User existingUser= this.userService.findByEmail(userDto.getEmail());
		
		if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
//			result.rejectValue("Email", null, "Email ALready Exist!!");
			return ResponseEntity.badRequest().body("Email already Registered!!");
		}
//		if (bindingResult.hasErrors()) {
//	        // If there are validation errors, return a bad request response with detailed error messages
//	        List<FieldError> errors = bindingResult.getFieldErrors();
//	        List<String> errorMessages = new ArrayList<>();
//	        for (FieldError error : errors) {
//	            errorMessages.add(error.getField() +" "+ error.getDefaultMessage());
//	        }
//	        return ResponseEntity.badRequest().body(errorMessages);
//	    }
		
		try {
			
			UserDto createUser = this.userService.createUser(userDto);
			return new ResponseEntity<>(createUser,HttpStatus.CREATED);
		}catch(ResourceNotFoundException e) {
			   String errorMessage = e.getMessage();
		        return ResponseEntity.badRequest().body(errorMessage);
		} catch (Exception e) {
		        // Handle other exceptions and return a generic error response
		        String errorMessage = "Failed to submit post. Please try again later.";
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}
	
	
	//get user from token
	@GetMapping("/user")
	  public ResponseEntity<UserDto> getCurrentUser(@RequestHeader("Authorization") String token) {
		
		logger.debug("getCurrentUser endpoint invoked.");
		
	    String userName = jwtService.extractUserNameFromToken(token.substring(7));

	    UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);
	    User user = this.modelMapper.map(userDetails, User.class);
	    UserDto userDto = this.modelMapper.map(user, UserDto.class);
	    
	    return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	    
	    
	}
	
	//update user
	@PutMapping("/users/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId)
	{
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(updatedUser,HttpStatus.OK);
	}
	
	//delete user
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
	{
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true) , HttpStatus.OK);
	}
	
	//get user by user id
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDto> getUserByUserId(@PathVariable Integer userId)
	{
		UserDto user = this.userService.getUserByUserId(userId);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	
	//get all users
	@GetMapping("/users")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto> allUser = this.userService.getAllUser();
		return new ResponseEntity<List<UserDto>>(allUser,HttpStatus.OK);
	}
	
	//Toggle Role

	    @PreAuthorize("hasRole('ROLE_ADMIN')")
		@PutMapping("/users/{userId}/toggle")
		public ResponseEntity<UserDto> toggleRole(@PathVariable Integer userId)
		{
			User user = this.userRepository.findById(userId).get();
//			Role role = this.roleRepository.getByRoleName(userDto.getRoleName());
//			user.setRole(role);
//			user.setRole("USER_ADMIN");
			User saved = this.userRepository.save(user);
			UserDto toggle_Admin = this.modelMapper.map(saved, UserDto.class);
			return new ResponseEntity<>(toggle_Admin,HttpStatus.OK);
			
		}
	
	@GetMapping("/users/export")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void exportToCsv(HttpServletResponse response) throws IOException, java.io.IOException{
		response.setContentType("text/csv");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime= dateFormat.format(new Date());
		String fileName = "users"+currentDateTime+".csv";
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachement; filename=" + fileName;
		response.setHeader(headerKey, headerValue);
		
		List<UserDto> listUsers = this.userService.getAllUser();
		
		ICsvBeanWriter csvWriter= new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader= {"User ID", "Username", "E-mail", "Password", "Phone", "Role"};
		String[] nameMapping = {"id" , "userName" , "email", "password", "phone", "role"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (UserDto user : listUsers)
		{
			csvWriter.write(user, nameMapping);
		}
		csvWriter.close();
		
	}
	
}
