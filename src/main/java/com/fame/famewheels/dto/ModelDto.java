package com.fame.famewheels.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ModelDto {
	
	private int modelId;
	@NotEmpty
	private String modelName;
	
	private String makeName;
	
	private MakeDto make;

}
