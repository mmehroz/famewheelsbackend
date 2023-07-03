package com.fame.famewheels.servicesimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.UserDto;
import com.fame.famewheels.entities.Role;
import com.fame.famewheels.entities.User;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.repositories.RoleRepository;
import com.fame.famewheels.repositories.UserRepository;
import com.fame.famewheels.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;

	// creating new user
	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
		Role role = this.roleRepository.findByRoleName(userDto.getRoleName());
		if(role==null) {
			throw new ResourceNotFoundException("User", "roleName", userDto.getRoleName());
		}
		user.setRole(role);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		try {
			User savedUser = this.userRepository.save(user);
			return this.modelMapper.map(savedUser, UserDto.class);
			
		}catch(Exception e){
	        throw new RuntimeException("Failed to create post. Please try again.", e);

		}
	}

	// updating user details
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
//		
//		Role role= this.roleRepository.findByRoleName(userDto.getRoleName());

		user.setUserName(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setPhone(userDto.getPhone());
//		user.setRole(role);

		// setting or updating user role
//		Role role = this.roleRepository.getByRoleName(userDto.getRoleName());
//		user.setRole(role);
//		user.setRole(userDto.getRole());

		User updatedUser = this.userRepository.save(user);
		return this.modelMapper.map(updatedUser, UserDto.class);
	}

	// get single user
	@Override
	public UserDto getUserByUserId(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
		return this.modelMapper.map(user, UserDto.class);
	}

	// get all users
	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map((user) -> this.modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		return userDtos;
	}

	// delete user
	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
		this.userRepository.delete(user);
	}

	@Override
	public User findByEmail(String email) {
		try {
			return this.userRepository.findByEmail(email).get();
			
		}catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public UserDto updateUserAsAuctioneer(UserDto userDto, Integer userId) {
		
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
//		
//		Role role= this.roleRepository.findByRoleName(userDto.getRoleName());
		
		user.setEmail(userDto.getEmail());
		user.setUserName(userDto.getUserName());
		
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setPhone(userDto.getPhone());
//		user.setRole(role);
		user.setIsAuctioneer(1);

		// setting or updating user role
//		Role role = this.roleRepository.getByRoleName(userDto.getRoleName());
//		user.setRole(role);
//		user.setRole(userDto.getRole());

		User updatedUser = this.userRepository.save(user);
		return this.modelMapper.map(updatedUser, UserDto.class);
	}
	@Override
	public UserDto updateUserAsBidder(UserDto userDto, Integer userId) {
		
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
//		
		Role role= this.roleRepository.findByRoleName("ROLE_BIDDER");
		
		user.setEmail(userDto.getEmail());
		user.setUserName(userDto.getUserName());
		
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setPhone(userDto.getPhone());
		user.setRole(role);

		// setting or updating user role
//		Role role = this.roleRepository.getByRoleName(userDto.getRoleName());
//		user.setRole(role);
//		user.setRole(userDto.getRole());

		User updatedUser = this.userRepository.save(user);
		return this.modelMapper.map(updatedUser, UserDto.class);
	}
}
