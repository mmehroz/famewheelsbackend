package com.fame.famewheels.services;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.fame.famewheels.dto.PostDto;
import com.fame.famewheels.dto.PostImageDto;

public interface PostImagesService {
	
	 //create post
//    PostImageDto createPost(PostImageDto postImageDto, Integer postId)throws IOException;
    
    //upload image
    ResponseEntity<String> uploadImages(MultipartFile [] files,PostDto postDto );
    
    ResponseEntity<String> updateImages(MultipartFile [] files, PostDto postDto);
    
    //get all posts by post Id
  	List<PostImageDto> getPostsByPostId (Integer postId);
  	
  	//get images by postId
  	List<File> getImageFilesByPostId(Integer postId);
  	
  	//get post images
  	List<String> getPostImages(Integer postId);
  	
  	void deletePostImages(Integer imageId);
  	
  	//getImages with Image Ids
  	ArrayList<ArrayList<Object>> getImagesByPostId(Integer postId);



}
