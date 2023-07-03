package com.fame.famewheels.dto;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AuctionAppointmentDto {

	
	private int appointmentId;
	private String appointmentDate;
	
	private String appointmentTime;
	
	private String userName;
	
	private int contactNo;
	private String email;
	
	private int branchId;
	
	private BranchDto branch;
	
}
