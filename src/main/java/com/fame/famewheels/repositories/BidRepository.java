package com.fame.famewheels.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fame.famewheels.dto.BidDto;
import com.fame.famewheels.entities.AuctionPost;
import com.fame.famewheels.entities.Bid;

public interface BidRepository extends JpaRepository<Bid, Integer>{

	List<Bid> findByAuctionPost(AuctionPost post);

}
