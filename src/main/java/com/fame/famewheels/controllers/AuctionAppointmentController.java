package com.fame.famewheels.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fame.famewheels.dto.AuctionAppointmentDto;
import com.fame.famewheels.dto.BranchDto;
import com.fame.famewheels.entities.AuctionAppointment;
import com.fame.famewheels.services.AuctionAppointmentService;

@RestController
@RequestMapping("/fame")
public class AuctionAppointmentController {
	
	
	@Autowired
	private AuctionAppointmentService appointmentService;
	
	@PostMapping("/createAuctionAppointment")
	public ResponseEntity<AuctionAppointmentDto> createAuctionAppointment(@ModelAttribute AuctionAppointmentDto appointment){
		AuctionAppointmentDto create= this.appointmentService.createAppointment(appointment);
		
		return new ResponseEntity<AuctionAppointmentDto>(create, HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllAppointments")
	public ResponseEntity<List<AuctionAppointmentDto>> getAllAppointments(){
		
		List<AuctionAppointmentDto> getAll =this.appointmentService.getAppointments();
		return new ResponseEntity<List<AuctionAppointmentDto>>(getAll, HttpStatus.OK);
		
	}
	
	@GetMapping("/getBranch")
	public ResponseEntity<List<BranchDto>> getBranches(){
		List<BranchDto> getAll= this.appointmentService.getBranches();
		return new ResponseEntity<List<BranchDto>>(getAll, HttpStatus.OK);
	}

}
