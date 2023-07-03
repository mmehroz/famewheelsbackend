package com.fame.famewheels.servicesimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.AuctionAppointmentDto;
import com.fame.famewheels.dto.BranchDto;
import com.fame.famewheels.entities.AuctionAppointment;
import com.fame.famewheels.entities.Branch;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.repositories.AuctionAppointmentRepository;
import com.fame.famewheels.repositories.BranchRepository;
import com.fame.famewheels.services.AuctionAppointmentService;

@Service
public class AuctionAppointmentServiceImpl implements AuctionAppointmentService {

	@Autowired
	private AuctionAppointmentRepository appointmentRepo;
	
	@Autowired
	private BranchRepository branchrepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public AuctionAppointmentDto createAppointment(AuctionAppointmentDto appointment) {
		Branch branch= this.branchrepository.findById(appointment.getBranchId()).orElseThrow(() -> 
		new ResourceNotFoundException("Branch", "branchId", appointment.getBranchId()));
		
		AuctionAppointment saveAppoint=this.modelMapper.map(appointment, AuctionAppointment.class);
		
		SimpleDateFormat date =new SimpleDateFormat("d/MM/yyyy");
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
		Date ndate =new Date();
		
		saveAppoint.setAppointmentDate(date.format(ndate));
		saveAppoint.setAppointmentTime(time.format(ndate));
		saveAppoint.setBranch(branch);
		
		try {
			AuctionAppointment savedAppoint= this.appointmentRepo.save(saveAppoint);
			return this.modelMapper.map(savedAppoint, AuctionAppointmentDto.class);
		}catch(Exception e) {
		        // Handle any database-related exceptions here
		        throw new RuntimeException("Failed to create post. Please try again.", e);
		}
	}

	@Override
	public List<AuctionAppointmentDto> getAppointments() {
		List<AuctionAppointment> getAll =this.appointmentRepo.findAll();
		List<AuctionAppointmentDto> getAllAppointments=getAll.stream().map((get) -> this.modelMapper.map(get, AuctionAppointmentDto.class))
				.collect(Collectors.toList());
		return getAllAppointments;
	}

	@Override
	public List<BranchDto> getBranches() {
		
		List<Branch> getAll=this.branchrepository.findAll();
		List<BranchDto> getAllBranches=getAll.stream().map((g) -> this.modelMapper.map(g, BranchDto.class)).collect(Collectors.toList());
		return getAllBranches;
	}

}
