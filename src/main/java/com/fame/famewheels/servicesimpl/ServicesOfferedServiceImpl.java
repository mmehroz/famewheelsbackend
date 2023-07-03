package com.fame.famewheels.servicesimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.ServicesOfferedDto;
import com.fame.famewheels.entities.ServicesOffered;
import com.fame.famewheels.entities.User;
import com.fame.famewheels.entities.VehicleServiceAssistant;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.repositories.ServicesOfferedRepository;
import com.fame.famewheels.repositories.UserRepository;
import com.fame.famewheels.repositories.VehicleServiceAssistantRepository;
import com.fame.famewheels.services.ServicesOfferedService;

@Service
public class ServicesOfferedServiceImpl implements ServicesOfferedService{
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VehicleServiceAssistantRepository vehicleServiceAssistantRepository;
	@Autowired
	private ServicesOfferedRepository servicesOfferedRepository;
	
	@Override
	public ServicesOfferedDto createService(ServicesOfferedDto servicesOfferedDto,Integer userId, Integer serviceAssistantId) {
		
		User user = this.userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		VehicleServiceAssistant vehicleServiceAssistant = this.vehicleServiceAssistantRepository.findById(serviceAssistantId)
		.orElseThrow(() -> new ResourceNotFoundException("ServiceAssistant", "serviceAssistantId", serviceAssistantId));
		
		ServicesOffered servicesOffered = this.modelMapper.map(servicesOfferedDto, ServicesOffered.class);
		servicesOffered.setVehicle_type(servicesOfferedDto.getVehicle_type());
		servicesOffered.setVehicle_model(servicesOfferedDto.getVehicle_model());
		servicesOffered.setLocation(servicesOfferedDto.getLocation());
		servicesOffered.setUser(user);
		servicesOffered.setVehicleServiceAssistant(vehicleServiceAssistant);
		
		ServicesOffered saveServicesOffered = this.servicesOfferedRepository.save(servicesOffered);
		return this.modelMapper.map(saveServicesOffered, ServicesOfferedDto.class);
	}
	
	
}
