package com.fame.famewheels.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServicesOfferedDto {
	
	private int service_id;
	
	@NotEmpty
	private String vehicle_type;
	@NotEmpty
	private String vehicle_model;
	private String location;
	
	private UserDto user;
	private ServicesOfferedDto servicesOffered;

}
