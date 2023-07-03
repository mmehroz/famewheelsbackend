package com.fame.famewheels.dto;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class CityDto {
	
	
	private int cityId;
	@NotBlank
	private String city;

}
