package com.fame.famewheels.services;

import com.fame.famewheels.dto.ServicesOfferedDto;

public interface ServicesOfferedService {
	//user wants to create service
	ServicesOfferedDto createService(ServicesOfferedDto ServicesOferedDto, Integer userId, Integer serviceAssistantId );
}
