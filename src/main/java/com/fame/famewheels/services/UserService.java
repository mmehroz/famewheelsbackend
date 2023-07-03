package com.fame.famewheels.services;

import java.util.List;

import com.fame.famewheels.dto.UserDto;
import com.fame.famewheels.entities.User;

public interface UserService {
	//create user
	UserDto createUser(UserDto userDto);
	
	//update user
	UserDto updateUser (UserDto userDto, Integer userId);
	
	//get user by user id
	UserDto getUserByUserId(Integer userId);
	 
	//get all users
	List<UserDto> getAllUser();
	 
	//delete user
	void deleteUser(Integer userId);
	
	public User findByEmail(String email);
	
	UserDto updateUserAsAuctioneer(UserDto userDto, Integer userId);
	
	UserDto updateUserAsBidder(UserDto userDto, Integer userId);
}
