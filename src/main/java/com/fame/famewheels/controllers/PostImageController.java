package com.fame.famewheels.controllers;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fame.famewheels.dto.ApiResponse;
import com.fame.famewheels.dto.PostDto;
import com.fame.famewheels.dto.PostImageDto;
import com.fame.famewheels.entities.Post;
import com.fame.famewheels.repositories.PostRepository;
import com.fame.famewheels.services.PostImagesService;
import com.fame.famewheels.services.PostService;

@RestController
@RequestMapping("/fame")
public class PostImageController {
	@Autowired
	private PostImagesService postImagesService;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PostService postService;
	@Autowired
	private PostRepository postRepository;
	@Value("${project.url}")
	private String url;
//	@Autowired
//	private PostImageRepository postImageRepository;
	
	
	//upload images controller to image directory
	@PostMapping("/uploadImages/{postId}")
	public ResponseEntity<String> uploadImages(@RequestParam("files") MultipartFile [] files, @PathVariable Integer postId )
	{
		Post post = this.postRepository.findById(postId).get();
		PostDto postDto = this.mapper.map(post, PostDto.class);
		
		 return postImagesService.uploadImages(files,postDto);
	}
	
	@PutMapping("/updateImages/{postId}")
	public ResponseEntity<String> updateImages(@RequestParam("files") MultipartFile [] files, @PathVariable Integer postId )
	{
		Post post = this.postRepository.findById(postId).get();
		PostDto postDto = this.mapper.map(post, PostDto.class);
		
		 return postImagesService.uploadImages(files,postDto);
	}
	
//	@GetMapping("/path")
//	public ResponseEntity<String> getImagesPath()
//	{
//		return ResponseEntity.ok(url);
//	}
	
	
//	@GetMapping("/{postId}")
//    public ResponseEntity<List<String>> getImagesByPostId(@PathVariable("postId") int postId) {
//        List<String> imageFilenames = 
//
//        if (imageFilenames.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok().body(imageFilenames);
//    }
	
	
	
	//get images name by postId
	@GetMapping("/post-images/{postId}")
	public ResponseEntity<List<PostImageDto>> getPostImagesByPostId(@PathVariable Integer postId)
	{
		List<PostImageDto> postsByPostId = this.postImagesService.getPostsByPostId(postId);
		return new ResponseEntity<List<PostImageDto>>(postsByPostId,HttpStatus.OK);
	}
	
	@GetMapping("/images/{postId}")
	public ResponseEntity<ArrayList<ArrayList<Object>>> getImagesByPostId(@PathVariable Integer postId)
	{
		ArrayList<ArrayList<Object>> postsByPostId = this.postImagesService.getImagesByPostId(postId);
		return new ResponseEntity<ArrayList<ArrayList<Object>>>(postsByPostId,HttpStatus.OK);
	}
	
	
	
	//get iMages By PostID
	
//	@GetMapping("/iii/{postId}")
//	public ResponseEntity<List<String>> getPostImagess(@PathVariable Integer postId) {
//	    
//		List<String> imagePaths = this.postImagesService.getPostImages(postId);
//		 if (!imagePaths.isEmpty()) {
//		        return ResponseEntity.ok(imagePaths);
//		    } else {
//		        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No image files found");
//		    }
//	}
	
//	 @GetMapping("posts/image/{postId}")
//	 public ResponseEntity<List<Resource>> getPostImages(@PathVariable Integer postId) {
//		    try {
//		        // Get the image paths based on the postId from the service
//		        List<String> imagePaths = this.postImagesService.getPostImages(postId);
//
//		        // Create a list to store the image resources
//		        List<Resource> imageResources = new ArrayList<>();
//
//		        for (String imagePath : imagePaths) {
////		        	File imageFile = new File(imagePath);
//
//		            // Create a Resource object from the image file path
//		            Resource imageResource = new UrlResource(imagePath);
//
//		            // Check if the resource exists and is readable
//		            if (imageResource.exists() && imageResource.isReadable()) {
//		                imageResources.add(imageResource);
//		            }
//		        }
//
//		        if (!imageResources.isEmpty()) {
//		            // Set the appropriate content type for the response
//		            HttpHeaders headers = new HttpHeaders();
//		            headers.setContentType(MediaType.IMAGE_JPEG);
//
//		            // Return the image files as a response
//		            return ResponseEntity.ok()
//		                    .headers(headers)
//		                    .body(imageResources);
//		        } else {
//		            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No image files found");
//		        }
//		    } catch (Exception e) {
//		        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving images", e);
//		    }
//		}
	
	
	@GetMapping("posts/image/{postId}")
	public ResponseEntity<List<String>> getPostImages(@PathVariable Integer postId) {
	    try {
	        List<String> imagePaths = this.postImagesService.getPostImages(postId);
	        List<String> modifiedPaths = new ArrayList<>();

	        // Replace local file system path with IP address in image URLs
	        String ipAddress = "192.168.18.8";
	        String prefixToRemove = "C:\\xampp\\htdocs\\";
	        String prefixToAppend = "http://" + ipAddress + "/";

	        for (String imagePath : imagePaths) {
	            String modifiedPath = imagePath.replace(prefixToRemove, prefixToAppend);
	            modifiedPaths.add(modifiedPath);
	        }

	        if (!modifiedPaths.isEmpty()) {
//	        	String coverImageUrl = modifiedPaths.get(0);
//	        	this.postService.postCover(postId, coverImageUrl);
	            return ResponseEntity.ok().body(modifiedPaths);
	        } else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No image files found");
	        }
	    } catch (Exception e) {
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving images", e);
	    }

//	        if (!imagePaths.isEmpty()) {
//	            return ResponseEntity.ok().body(imagePaths);
//	        } else {
//	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No image files found");
//	        }
//	    } catch (Exception e) {
//	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving images", e);
//	    }
	}
  
	    
	
	//get images by using post id
	@GetMapping("/getImageFiles/{postId}")
	public ResponseEntity<List<Resource>> getImageFiles(@PathVariable Integer postId ) throws MalformedURLException
	{
		List<File> fileObjectList = this.postImagesService.getImageFilesByPostId(postId);
		List<Resource> resourcesList = new ArrayList<>();
		for (File fileObject : fileObjectList)
		{
//			 Resource resource = new FileSystemResource(fileObject);
			Resource resource = new UrlResource(fileObject.toURI().toURL());
		        resourcesList.add(resource);
		}
		return ResponseEntity.ok(resourcesList);
	}
	
	@DeleteMapping("/deleteImages/{imageId}")
	public ApiResponse DeleteImages(@PathVariable Integer imageId) {
		
//		try {
//		      Path file = root.resolve(filename);
//		      return Files.deleteIfExists(file);
//		    } catch (IOException e) {
//		      throw new RuntimeException("Error: " + e.getMessage());
//		    }
		this.postImagesService.deletePostImages(imageId);
		
		return new ApiResponse("Image Deleted Successfully!!", true);
	}
}
