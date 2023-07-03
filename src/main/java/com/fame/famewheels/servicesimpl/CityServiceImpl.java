package com.fame.famewheels.servicesimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.CityDto;
import com.fame.famewheels.entities.City;
import com.fame.famewheels.repositories.CityRepository;
import com.fame.famewheels.services.CityService;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityRepository CitiesRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CityDto> getCities() {
		List<City> cities = this.CitiesRepository.findAll();
		List<CityDto> city=cities.stream().map((c)-> this.modelMapper.map(c, CityDto.class)).collect(Collectors.toList());
		return city;
		
	}
	
	
	

}
