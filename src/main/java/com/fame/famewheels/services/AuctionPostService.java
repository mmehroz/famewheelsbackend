package com.fame.famewheels.services;

import java.util.List;
import java.util.Map;

import com.fame.famewheels.dto.AuctionPostDto;
import com.fame.famewheels.dto.PostDto;
import com.fame.famewheels.dto.UserDto;

public interface AuctionPostService {
	

    //create post	
	AuctionPostDto createAuctionPost(AuctionPostDto auctionPost, UserDto user, PostDto post);
	
	List<AuctionPostDto> getUpcomingPost(int pageNo, int pageSize);
	
	List<AuctionPostDto> currentAuction();
	
	AuctionPostDto getAuctionPostById(int auctionPostId);
	
	int getPostCountForToday(String date, String time);
	
	Map<String, Integer> getPostCount();

}
