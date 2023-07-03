package com.fame.famewheels.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bid {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int bidId;
		
		@Column(nullable = false)
		private String BidAmount;
		
		@ManyToOne
		@JoinColumn(name="user_id")
		private User user;
		
		@ManyToOne
		@JoinColumn(name="auction_post_id")
		private AuctionPost auctionPost;
		
}
