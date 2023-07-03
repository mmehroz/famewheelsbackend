package com.fame.famewheels.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ModelYearDto {
	
	private int yearId;
	@NotEmpty
	private String year;
	

}
