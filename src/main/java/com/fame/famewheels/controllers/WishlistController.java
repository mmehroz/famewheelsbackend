package com.fame.famewheels.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fame.famewheels.dto.WishlistDto;
import com.fame.famewheels.services.WishlistService;

@RestController
@RequestMapping("/fame")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;

	@PostMapping("/addToWishlist")
	public ResponseEntity<?> AddVehicleToWishlist(@ModelAttribute WishlistDto wishlist){
		
		WishlistDto wish=this.wishlistService.addToWishlist(wishlist);
		return ResponseEntity.ok("Vehicle Added to wishlist!!");
	}
	
	
	@GetMapping("/getWishlistByUserId")
	public ResponseEntity<List<WishlistDto>> getWishlistByUserId(@RequestParam int userId){
		List<WishlistDto> getListByUserId= this.wishlistService.getWishList(userId);
		return new ResponseEntity<List<WishlistDto>>(getListByUserId, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletewishlist")
	public ResponseEntity<?> DeleteVehicleFromWishlist(@RequestParam int wishlistId){
		this.wishlistService.DeleteFromWishlist(wishlistId);
		return ResponseEntity.ok("The Vehicle is removed from wishlist!!");
	}
}
