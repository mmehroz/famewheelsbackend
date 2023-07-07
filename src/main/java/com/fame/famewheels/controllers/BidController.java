package com.fame.famewheels.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fame.famewheels.dto.BidDto;
import com.fame.famewheels.services.BidService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/fame")
public class BidController {
	
	@Autowired
	private BidService bidService;
	
	@PostMapping("/Bid")
	public ResponseEntity<?> bid(@Valid @ModelAttribute BidDto bid, BindingResult result){
		if(result.hasErrors()) {
			List<FieldError> errors =result.getFieldErrors();
			List<String> errMsg= new ArrayList<>();
			for(FieldError error: errors) {
				errMsg.add(error.getField()+": "+ error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(errMsg);
		}
		
		BidDto postbid =this.bidService.Postbid(bid);
		return ResponseEntity.ok("Bid Submitted!!");
	}
	
	@GetMapping("/getBidsByPostId")
	public ResponseEntity<List<BidDto>> getBidsByPostId(@RequestParam Integer auctionPostId){
		List<BidDto> getBids= this.bidService.getAllBidsByPostId(auctionPostId);
		return new ResponseEntity<List<BidDto>>(getBids, HttpStatus.OK);
		
	}
}
