package com.fame.famewheels.services;

import java.util.List;

import com.fame.famewheels.dto.ModelDto;

public interface ModelService {

	List<ModelDto> getAllModel();
	
	List<ModelDto> getByMakeId(Integer makeId);
}
