package com.fame.famewheels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fame.famewheels.entities.PostType;

public interface PostTypeRepository extends JpaRepository<PostType, Integer>{

	PostType findByTypeName(String string);

}
