package com.fame.famewheels.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VehicleServiceAssistantDto {
	private int id;
	@NotEmpty
	private String serviceAvail;
}
