package com.fame.famewheels.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VehicleInspectionDto {
	private int inspectionId; 
	@NotEmpty(message = "fullName is required ")
	private String fullName;
	@NotEmpty(message = "Vehicle Type is required ")
	private String vehicleType;
	@NotEmpty(message = "Address is required ")
	private String address;
	@NotEmpty(message = "InspectionSlot is required ")
	private String inspectionSlot;
	@NotEmpty(message = "Phone is required ")
	private String phone;
	private String description;
	@NotEmpty(message = "City is required ")
	private String city;
}
