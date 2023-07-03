package com.fame.famewheels.servicesimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.BidDto;
import com.fame.famewheels.entities.AuctionPost;
import com.fame.famewheels.entities.Bid;
import com.fame.famewheels.entities.User;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.repositories.AuctionPostRepository;
import com.fame.famewheels.repositories.BidRepository;
import com.fame.famewheels.repositories.UserRepository;
import com.fame.famewheels.services.BidService;

@Service
public class BidServiceImpl implements BidService {
	
	Logger logger= LoggerFactory.getLogger(BidServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuctionPostRepository auctionPostRepository;
	@Autowired
	private BidRepository bidRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public BidDto Postbid(BidDto bid) {
		AuctionPost auctionPost= this.auctionPostRepository.findById(bid.getAuctionId()).orElseThrow(() ->
		new ResourceNotFoundException("AuctionPost", "auctionPostId", bid.getAuctionId()));
		
		logger.info("auction {}", auctionPost);
		
		User user=this.userRepository.findById(bid.getId()).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", bid.getId()));
		Bid Addbid= this.modelMapper.map(bid, Bid.class);
		
		Addbid.setAuctionPost(auctionPost);
		Addbid.setUser(user);
		
		try {
			Bid saveBid =this.bidRepository.save(Addbid);
			return this.modelMapper.map(saveBid, BidDto.class);
		}
		catch(Exception e) {
			throw new RuntimeException("Failed to create post. Please try again.", e);		}
	}

	@Override
	public List<BidDto> getAllBidsByPostId(int auctionPostId) {
		AuctionPost post= this.auctionPostRepository.findById(auctionPostId).
				orElseThrow(() -> new ResourceNotFoundException("AuctionPost", "auctionPostId", auctionPostId));
		
		List<Bid> getBids= this.bidRepository.findByAuctionPost(post);
//		if(getBids==null) {
//			throw new ResourceNotFoundException("Bid", "bidId", )
//		}
		
		List<BidDto> getBidAmounts=getBids.stream().map((bid) -> this.modelMapper.map(bid, BidDto.class)).collect(Collectors.toList());
		return getBidAmounts;
	}

	

}
