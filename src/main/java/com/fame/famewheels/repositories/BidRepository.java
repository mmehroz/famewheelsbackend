package com.fame.famewheels.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fame.famewheels.dto.BidDto;
import com.fame.famewheels.entities.AuctionPost;
import com.fame.famewheels.entities.Bid;

public interface BidRepository extends JpaRepository<Bid, Integer>{

	
	@Query(value="SELECT * FROM bid WHERE auction_post_id=:auctionPostId ORDER by(bid_amount) DESC;", nativeQuery=true)
	List<Bid> findByAuctionPost(int auctionPostId);

}
