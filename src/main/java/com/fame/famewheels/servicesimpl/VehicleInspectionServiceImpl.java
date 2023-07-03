package com.fame.famewheels.servicesimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.CategoryDto;
import com.fame.famewheels.dto.PostDto;
import com.fame.famewheels.dto.VehicleInspectionDto;
import com.fame.famewheels.entities.Category;
import com.fame.famewheels.entities.Post;
import com.fame.famewheels.entities.User;
import com.fame.famewheels.entities.VehicleInspection;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.repositories.UserRepository;
import com.fame.famewheels.repositories.VehicleInspectionRepository;
import com.fame.famewheels.services.InspectionService;
@Service
public class VehicleInspectionServiceImpl implements InspectionService {
	
	@Autowired
	private VehicleInspectionRepository inspectionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public VehicleInspectionDto createVehicleInspection(VehicleInspectionDto vehicleInspectionDto,Integer userId) {
		User user = this.userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
//		return user;
		
		VehicleInspection vehicleInspection = this.modelMapper.map(vehicleInspectionDto, VehicleInspection.class);
		vehicleInspection.setAddress(vehicleInspectionDto.getAddress());
		vehicleInspection.setFullName(user.getUsername());
		vehicleInspection.setInspectionSlot(vehicleInspectionDto.getInspectionSlot());
		vehicleInspection.setCity(vehicleInspectionDto.getCity());
		vehicleInspection.setVehicleType(vehicleInspectionDto.getVehicleType());
		vehicleInspection.setPhone(vehicleInspectionDto.getPhone());
		vehicleInspection.setUser(user);
		
		VehicleInspection saveInspection = this.inspectionRepository.save(vehicleInspection);
		
		return this.modelMapper.map(saveInspection, VehicleInspectionDto.class);
	}
	
	public List<VehicleInspectionDto> getInspection(){
		List<VehicleInspection> findAll = this.inspectionRepository.findAll();
		List<VehicleInspectionDto> getInspection = findAll.stream().map((get)-> this.modelMapper.map(get, VehicleInspectionDto.class)).collect(Collectors.toList());
		return getInspection; 
	}

	@Override
	public List<VehicleInspectionDto> getByUserId(Integer id) {
		User user = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user", "userId", id));
		List<VehicleInspection> inspections = this.inspectionRepository.getByUser(user);
		List<VehicleInspectionDto> inspection = inspections.stream().map((post)-> this.modelMapper.map(post, VehicleInspectionDto.class)).collect(Collectors.toList());
		return inspection;
	}
	
	

}
