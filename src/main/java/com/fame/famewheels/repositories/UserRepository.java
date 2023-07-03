package com.fame.famewheels.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fame.famewheels.entities.Role;
import com.fame.famewheels.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	
//	User findByRole(Role role);

}
