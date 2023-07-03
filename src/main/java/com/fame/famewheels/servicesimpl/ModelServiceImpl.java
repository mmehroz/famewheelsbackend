package com.fame.famewheels.servicesimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.ModelDto;
import com.fame.famewheels.entities.Make;
import com.fame.famewheels.entities.Model;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.repositories.MakeRepository;
import com.fame.famewheels.repositories.ModelRepository;
import com.fame.famewheels.services.ModelService;

@Service
public class ModelServiceImpl implements ModelService {

	@Autowired
	private ModelRepository modelRepository;
	
	@Autowired
	private MakeRepository makeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ModelDto> getAllModel() {
		List<Model> getModel =this.modelRepository.findAll();
		List<ModelDto> getAll =getModel.stream().map((m) -> this.modelMapper.map(m, ModelDto.class)).collect(Collectors.toList());
		return getAll;
	}

	@Override
	public List<ModelDto> getByMakeId(Integer makeId) {
		
		Make make=this.makeRepository.findById(makeId).orElseThrow(()-> new ResourceNotFoundException("make", "makeId", makeId));
		
		List<Model> getmodel= this.modelRepository.getByMake(make);
		List<ModelDto> getAll= getmodel.stream().map((m) -> this.modelMapper.map(m, ModelDto.class)).collect(Collectors.toList());
		
		return getAll;
	}
	
	

}
