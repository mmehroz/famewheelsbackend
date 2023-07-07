package com.fame.famewheels.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fame.famewheels.entities.User;
import com.fame.famewheels.entities.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

	List<Wishlist> findByUser(User user);

	
	
}
