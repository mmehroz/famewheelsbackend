package com.fame.famewheels.servicesimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.BiddingTimeDto;
import com.fame.famewheels.entities.BiddingTime;
import com.fame.famewheels.repositories.BiddingTimeRepository;
import com.fame.famewheels.services.BiddingTimeService;

@Service
public class BiddingTimeServiceImpl implements BiddingTimeService {

	@Autowired
	private BiddingTimeRepository biddingTimeRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	public List<BiddingTimeDto> getTimeSlot(){
		List<BiddingTime> slot=this.biddingTimeRepo.findAll();
		List<BiddingTimeDto> timeSlot= slot.stream().map((sl) -> this.modelMapper.map(sl, BiddingTimeDto.class)).collect(Collectors.toList());
		
		return timeSlot;
	}
}
