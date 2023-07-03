package com.fame.famewheels.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class AuctionPost {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int auctionPostId;
		@Column(nullable=false)
		private long startingAmount;
		
		
//		@Temporal(TemporalType.DATE)
		@Column(nullable=false)
		private String auctionDate;
		
//		@Temporal(TemporalType.TIME)
		@Column(nullable=false)
		private String auctionStartTime;
//		@Temporal(TemporalType.TIME)
		@Column(nullable=false)
		private String auctionEndTime;
		
		//foreign key UserId
		
		@ManyToOne
		@JoinColumn(name="user_id")
		private User user;
		
		@ManyToOne
		@JoinColumn(name="post_id")
		private Post post;
		
		private int status;
		
		@OneToMany(mappedBy ="auctionPost", fetch = FetchType.LAZY)
		private List<Bid> bid=new ArrayList<>();
		
		
		

}
