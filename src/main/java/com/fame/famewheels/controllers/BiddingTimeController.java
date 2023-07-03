package com.fame.famewheels.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fame.famewheels.dto.BiddingTimeDto;
import com.fame.famewheels.services.BiddingTimeService;

@RestController
@RequestMapping("/fame")
public class BiddingTimeController {
	
	@Autowired
	private BiddingTimeService biddingTimeService;
	
	@GetMapping("/getTimeSlot")
	public ResponseEntity<List<BiddingTimeDto>> getBiddingSlot(){
		List<BiddingTimeDto>  getTime=this.biddingTimeService.getTimeSlot();
		
		return new ResponseEntity<List<BiddingTimeDto>>(getTime, HttpStatus.OK);
	}

}
