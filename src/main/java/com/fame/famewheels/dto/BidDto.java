package com.fame.famewheels.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BidDto {
	
	private int bidId;
	
	@NotEmpty(message="BidAmount Type is required")
	private String BidAmount;
	
	
	private int auctionId;
	private int id;
	
	private UserDto user;
	private AuctionPostDto auctionPost;
	
}
