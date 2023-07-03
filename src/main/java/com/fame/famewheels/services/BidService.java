package com.fame.famewheels.services;

import java.util.List;

import com.fame.famewheels.dto.BidDto;

public interface BidService {

	
	BidDto Postbid(BidDto bid);
	
	List<BidDto> getAllBidsByPostId(int auctionPostId);
}
