package com.fame.famewheels.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fame.famewheels.dto.UserDto;
import com.fame.famewheels.services.JwtService;

@RestController
@RequestMapping("/fame")
@CrossOrigin(origins = "http://192.168.18.246:3000")
public class JwtController {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody UserDto userDto)
	{
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));
		
		if(authentication.isAuthenticated())
		{
			return jwtService.generateToken(userDto.getUserName()) ;	
		}else {
			throw new UsernameNotFoundException("Inavalid user request..!");
		}
		
	}
}
