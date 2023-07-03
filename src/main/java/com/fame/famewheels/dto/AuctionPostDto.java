package com.fame.famewheels.dto;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AuctionPostDto {

	private int auctionPostId;
	
	@NotNull
	private long startingAmount;
	@NotEmpty
	private String auctionDate;
	@NotEmpty
	private String auctionStartTime;
	@NotEmpty
	private String auctionEndTime;
	private int status;
	
	private String email;
	
	private PostDto post;
	private UserDto user;
	
}
