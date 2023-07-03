package com.fame.famewheels.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fame.famewheels.entities.AuctionPost;

public interface AuctionPostRepository extends JpaRepository<AuctionPost, Integer> {

	@Query(value="Select * from auction_post where (auction_date>:date) or (auction_date=:date and auction_start_time>:time)  ", 
			nativeQuery=true)
	Page<AuctionPost> findUpcoming(@Param("date") String date, @Param("time") String time, Pageable p);
	
	
	@Query(value="Select * from auction_post where auction_date=:date and auction_start_time<=:time and auction_end_time>=:time",
			nativeQuery=true)
	List<AuctionPost> findCurrentPost(@Param("date") String date, @Param("time") String time);
	
	@Query(value="Select count(auction_post_id) from auction_post where auction_date=:date and auction_start_time>:time ", 
			nativeQuery=true)
	int getUpcomingPostCount(@Param("date") String date, @Param("time") String time);

	@Query(value="Select count(auction_post_id) from auction_post where auction_date=:date and auction_start_time<=:time and auction_end_time>=:time",
			nativeQuery=true)
	int getCurrentPostCount(@Param("date")String date, @Param("time")String time);
}
