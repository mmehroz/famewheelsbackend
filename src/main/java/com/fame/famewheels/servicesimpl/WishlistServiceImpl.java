package com.fame.famewheels.servicesimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.WishlistDto;
import com.fame.famewheels.entities.Post;
import com.fame.famewheels.entities.User;
import com.fame.famewheels.entities.Wishlist;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.repositories.PostRepository;
import com.fame.famewheels.repositories.UserRepository;
import com.fame.famewheels.repositories.WishlistRepository;
import com.fame.famewheels.services.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistRepository wishlistRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Override
	public WishlistDto addToWishlist(WishlistDto wishlist) {
		Wishlist wish=this.modelMapper.map(wishlist, Wishlist.class);
		
		User user=this.userRepo.findById(wishlist.getId())
				.orElseThrow(()-> new ResourceNotFoundException("User", "id", wishlist.getId()));
		
		Post post=this.postRepo.findById(wishlist.getPostId())
				.orElseThrow(()-> new ResourceNotFoundException("Post", "postId", wishlist.getPostId()));
		wish.setPost(post);
		wish.setUser(user);
		
		try {
			Wishlist addWishList=this.wishlistRepo.save(wish);
			return this.modelMapper.map(addWishList, WishlistDto.class);
		}catch(Exception e) {
			throw new RuntimeException("Wishlist could not be added!"+ e.getMessage());
		}
	}

	@Override
	public List<WishlistDto> getWishList(int userId) {
		User user= this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		List<Wishlist> wishlist=this.wishlistRepo.findByUser(user);
		List<WishlistDto> list=wishlist.stream().map((l) -> this.modelMapper.map(l, WishlistDto.class)).collect(Collectors.toList());
		return list;
	}

	@Override
	public void DeleteFromWishlist(int WishlistId) {
		Wishlist wishlist=this.wishlistRepo.findById(WishlistId)
				.orElseThrow(() -> new ResourceNotFoundException("Wishlist", "wishlistId", WishlistId));
		
		this.wishlistRepo.delete(wishlist);
	}

}
