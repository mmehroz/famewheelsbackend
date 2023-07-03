package com.fame.famewheels.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BranchDto {
	
	
	private int branchId;
	private String branchName;
	private String location;
	private int status;
	
	private String cityName;
	
	private CityDto city;
	

}
