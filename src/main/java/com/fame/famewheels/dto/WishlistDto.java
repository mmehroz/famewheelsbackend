package com.fame.famewheels.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WishlistDto {

	
	private int wishlistId;
	
	private int postId;
	private int id;
	
	private UserDto user;
	private PostDto post;
}
