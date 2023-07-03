package com.fame.famewheels.services;

import java.util.List;

import com.fame.famewheels.dto.AuctionAppointmentDto;
import com.fame.famewheels.dto.BranchDto;

public interface AuctionAppointmentService {

	
	AuctionAppointmentDto createAppointment(AuctionAppointmentDto appointment);
	
	List<AuctionAppointmentDto> getAppointments();
	
	List<BranchDto> getBranches();
}
