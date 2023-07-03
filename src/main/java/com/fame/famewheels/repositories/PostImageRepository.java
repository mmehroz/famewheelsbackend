package com.fame.famewheels.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fame.famewheels.dto.PostImageDto;
import com.fame.famewheels.entities.Post;
import com.fame.famewheels.entities.PostImages;

public interface PostImageRepository extends JpaRepository<PostImages, Integer> {
	
	List<PostImages> findByPost(Post post);

	List<PostImageDto> getByPost(Post post);
	
//	@Query("SELECT pi.filename FROM PostImage pi WHERE pi.post.id = :postId")
//    List<String> findFilenamesByPostId(@Param("postId") int postId);

}
