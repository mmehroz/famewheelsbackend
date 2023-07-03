package com.fame.famewheels.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class VehicleDto {
	
	private int vehicle_id;
	@NotEmpty
	private String model;
	@NotEmpty
	private String type;
	@NotEmpty
	private int price;
	@NotEmpty
	private String description;
	private String image;
	
	private UserDto user;
	private CategoryDto category;

}
