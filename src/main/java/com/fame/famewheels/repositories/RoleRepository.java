package com.fame.famewheels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fame.famewheels.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	public Role findByRoleName(String roleName);

}
