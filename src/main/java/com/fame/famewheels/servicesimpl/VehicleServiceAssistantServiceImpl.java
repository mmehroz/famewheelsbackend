package com.fame.famewheels.servicesimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.VehicleServiceAssistantDto;
import com.fame.famewheels.entities.VehicleServiceAssistant;
import com.fame.famewheels.repositories.VehicleServiceAssistantRepository;
import com.fame.famewheels.services.VehicleServiceAssistantService;
@Service
public class VehicleServiceAssistantServiceImpl implements VehicleServiceAssistantService {
	@Autowired
	private VehicleServiceAssistantRepository assistantRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	//create service assistant
	@Override
	public VehicleServiceAssistantDto createService(VehicleServiceAssistantDto assistantDto) {
		
		
		VehicleServiceAssistant vehicleServiceAssistant = this.modelMapper.map(assistantDto, VehicleServiceAssistant.class);
		vehicleServiceAssistant.setServiceAvail(assistantDto.getServiceAvail());
		VehicleServiceAssistant savedVehicleAssistanf = this.assistantRepository.save(vehicleServiceAssistant);
		return this.modelMapper.map(savedVehicleAssistanf, VehicleServiceAssistantDto.class);
	}

	
	//get all vehicle assistant
	@Override
	public List<VehicleServiceAssistantDto> getAllServices() {
		List<VehicleServiceAssistant> findAll = this.assistantRepository.findAll();
		List<VehicleServiceAssistantDto> getAllVehicleAssistantDto = findAll.stream().map((getAll)-> this.modelMapper.map(getAll, VehicleServiceAssistantDto.class)).collect(Collectors.toList());
		return getAllVehicleAssistantDto;		
	}
	
	
	
	
	
	
	

}
