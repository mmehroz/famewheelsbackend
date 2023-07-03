package com.fame.famewheels.servicesimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.ModelYearDto;
import com.fame.famewheels.entities.ModelYear;
import com.fame.famewheels.repositories.ModelYearRepository;
import com.fame.famewheels.services.ModelYearService;

@Service
public class ModelYearServiceImpl implements ModelYearService{

	@Autowired
	private ModelYearRepository modelYearRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public List<ModelYearDto> getAll() {
		List<ModelYear> getModelYear= this.modelYearRepository.findAll();
		
		List<ModelYearDto> getyear= getModelYear.stream().map((m) -> modelMapper.map(m, ModelYearDto.class)).collect(Collectors.toList());
		
		return getyear;
	}

}
