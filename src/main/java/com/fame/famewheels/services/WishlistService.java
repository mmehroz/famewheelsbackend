package com.fame.famewheels.services;

import java.util.List;

import com.fame.famewheels.dto.WishlistDto;

public interface WishlistService {

	WishlistDto addToWishlist(WishlistDto wishlist);
	
	List<WishlistDto> getWishList(int userId);
	
	void DeleteFromWishlist(int WishlistId);
}
