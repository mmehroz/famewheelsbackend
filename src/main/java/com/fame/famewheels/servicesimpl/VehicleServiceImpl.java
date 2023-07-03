package com.fame.famewheels.servicesimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.VehicleDto;
import com.fame.famewheels.entities.Category;
import com.fame.famewheels.entities.User;
import com.fame.famewheels.entities.Vehicles;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.repositories.CategoryRepository;
import com.fame.famewheels.repositories.UserRepository;
import com.fame.famewheels.repositories.VehiclesRepository;
import com.fame.famewheels.services.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private VehiclesRepository vehiclesRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	
	//Create Post Vehicle
	@Override
	public VehicleDto createPostVehicle(VehicleDto vehicleDto, Integer userId, Integer categoryId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categorId", categoryId));
				
		
		Vehicles vehicle = this.modelMapper.map(vehicleDto, Vehicles.class);
		vehicle.setUser(user);
		vehicle.setCategory(category);
		
		Vehicles saveVehicle = this.vehiclesRepository.save(vehicle);
		
		return this.modelMapper.map(saveVehicle, VehicleDto.class);
	}
	
	
	//update Vehicle
	@Override
	public VehicleDto updateVehicle(VehicleDto vehicleDto, Integer vehicleId) {
		Vehicles vehicle = this.vehiclesRepository.findById(vehicleId)
		.orElseThrow(() -> new ResourceNotFoundException("Vehicle", "vehicleId", vehicleId));
		
		vehicle.setType(vehicleDto.getType());
		vehicle.setModel(vehicleDto.getModel());
		vehicle.setDescription(vehicleDto.getDescription());
		vehicle.setImage(vehicleDto.getImage());
		vehicle.setPrice(vehicleDto.getPrice());
		Vehicles updatedVehicle = this.vehiclesRepository.save(vehicle);
		return this.modelMapper.map(updatedVehicle, VehicleDto.class);

	}
	
	
	//Delete Vehicle
	@Override
	public void deletePost(Integer vehicleId) {
		Vehicles vehicle = this.vehiclesRepository.findById(vehicleId)
		.orElseThrow(() -> new ResourceNotFoundException("Vehicle", "vehicleId", vehicleId));
		this.vehiclesRepository.delete(vehicle);
		
		
	}
	
	// get vehicle by vehicle id
	@Override
	public VehicleDto getVehicleById(Integer vehicleId) {
		Vehicles vehicle = this.vehiclesRepository.findById(vehicleId)
		.orElseThrow(() -> new ResourceNotFoundException("Vehicle", "vehicleId", vehicleId));
		return this.modelMapper.map(vehicle, VehicleDto.class);
	}
	
	//get all vehicles
	@Override
	public List<VehicleDto> getAllVehicles() {
		List<Vehicles> vehicles = this.vehiclesRepository.findAll();
		List<VehicleDto> vehicleDtos = vehicles.stream().map((vehicle)-> this.modelMapper.map(vehicle, VehicleDto.class)).collect(Collectors.toList());
		return vehicleDtos;
	}
	
	//get vehicles by category id
	@Override
	public List<VehicleDto> getVehiclesByCategoryId(Integer categoryId) {
		Category cate = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", categoryId));
		List<Vehicles> categoryVehicles = this.vehiclesRepository.findByCategory(cate);
		List<VehicleDto> vehicleDtos = categoryVehicles.stream().map((catego)-> this.modelMapper.map(catego, VehicleDto.class)).collect(Collectors.toList());
		return vehicleDtos;
	}
	
	
	//get vehicles by user id
	@Override
	public List<VehicleDto> getVehiclesByUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
		List<Vehicles> vehicles = this.vehiclesRepository.findByUser(user);
		List<VehicleDto> vehicleDtos = vehicles.stream().map((vehicle)-> this.modelMapper.map(vehicle, VehicleDto.class)).collect(Collectors.toList());
		return vehicleDtos;
	}
	
	//search vehicles
	@Override
	public List<VehicleDto> searchVehicles(String keyword) {
		return null;
	}
	

}
