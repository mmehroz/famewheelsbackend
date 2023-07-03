package com.fame.famewheels.servicesimpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fame.famewheels.dto.PostDto;
import com.fame.famewheels.dto.PostImageDto;
import com.fame.famewheels.entities.Post;
import com.fame.famewheels.entities.PostImages;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.repositories.PostImageRepository;
import com.fame.famewheels.repositories.PostRepository;
import com.fame.famewheels.services.PostImagesService;
import com.fame.famewheels.services.PostService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostImageServiceImpl implements PostImagesService {

	@Value("${project.image}")
	private String uploads;
	@Autowired
	private PostImageRepository postImageRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PostService postService;

	
	//create post
//	@Override
//	public PostImageDto createPost(PostImageDto postImageDto, Integer postId) {
//
//		Post post = this.postRepository.findById(postId)
//		.orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
//
//		PostImages postImages = this.modelMapper.map(postImageDto, PostImages.class);
//		postImages.setFilename(postImageDto.getFilename());
//		postImages.setPost(post);
//		PostImages save = this.postImageRepository.save(postImages);
//
//		return this.modelMapper.map(save, PostImageDto.class);
//	}

	//upload multiple post images to images directory
	@Override
	public ResponseEntity<String> uploadImages(MultipartFile[] files, PostDto postDto) {
		String coverImageUrl = null;
		//loop for multiple images
		for(MultipartFile file : files)
		{
			 try {
				 
				 //Get file name
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                int rand= (int)(Math.random()*10000);
                if(!fileName.isBlank()) {
                	
                	fileName=rand+fileName;
                }
                //get post ID
                int postId = postDto.getPostId();
                //create folder if not created
        		File f = new File(uploads+"/"+postId);
        		if(!f.exists())
        		{
        			f.mkdir();
        		}
                
        		//get FilePath till image Name
                Path filePath = Paths.get(uploads+"/"+postId + File.separator + fileName);
                
                
                Files.write(filePath, file.getBytes());
                
                if (coverImageUrl == null) {
                    // First image is the cover image
                    coverImageUrl = fileName;
                }
                
                PostImageDto image = new PostImageDto();
                image.setFilename(fileName);
                image.setPost(postDto);
                PostImages postImages = this.modelMapper.map(image, PostImages.class);
               this.postImageRepository.save(postImages);
			 }
			 catch(IOException e)
			 {
				 e.printStackTrace();
		         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");    
			 }
		}
		
		// Replace local file system path with desired URL
//	    String ipAddress = "192.168.18.8";
//	    String prefixToRemove = "C:\\xampp\\htdocs\\";
//	    String prefixToAppend = "http://" + ipAddress + "/";
		// Store the cover image URL in the post
	    if (coverImageUrl != null) {
//	    	  coverImageUrl = coverImageUrl.replace(prefixToRemove, prefixToAppend);
	        this.postService.postCover(postDto.getPostId(), coverImageUrl);
	    }
		
		return ResponseEntity.ok("Images upload Successfully");
		}
	
	
	@Override
	public ResponseEntity<String> updateImages(MultipartFile[] files, PostDto postDto) {
		
		int postId = postDto.getPostId();
		
		Post postdata = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		
		String coverImageUrl = postdata.getCover();
		
		
//		String coverImageUrl = null;
		//loop for multiple images
		for(MultipartFile file : files)
		{
			 try {
				 
				 //Get file name
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                 
                int rand= (int)(Math.random()*10000);
                
                if(!fileName.isBlank()) {
            	
            	fileName=rand+fileName;
            	}
                //get post ID
                
                //create folder if not created
        		File f = new File(uploads+"/"+postId);
        		if(!f.exists())
        		{
        			f.mkdir();
        		}
                
        		//get FilePath till image Name
                Path filePath = Paths.get(uploads+"/"+postId + File.separator + fileName);
                
                
                Files.write(filePath, file.getBytes());
                
                if (coverImageUrl == null) {
                    // First image is the cover image
//                    coverImageUrl = filePath.toString();
                  coverImageUrl = fileName;

                }
                
                PostImageDto image = new PostImageDto();
                image.setFilename(fileName);
                image.setPost(postDto);
                PostImages postImages = this.modelMapper.map(image, PostImages.class);
               this.postImageRepository.save(postImages);
			 }
			 catch(IOException e)
			 {
				 e.printStackTrace();
		         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");    
			 }
		}
		
		// Replace local file system path with desired URL
//	    String ipAddress = "192.168.18.8";
//	    String prefixToRemove = "C:\\xampp\\htdocs\\";
//	    String prefixToAppend = "http://" + ipAddress + "/";
		// Store the cover image URL in the post
	    if (coverImageUrl != null) {
//	    	  coverImageUrl = coverImageUrl.replace(prefixToRemove, prefixToAppend);
	        this.postService.postCover(postDto.getPostId(), coverImageUrl);
	    }
		
		return ResponseEntity.ok("Images upload Successfully");
		}
	
	//get postsImages names by postID
	@Override
	public List<PostImageDto> getPostsByPostId(Integer postId) {
		
		Post post = this.postRepository.findById(postId).
				orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));
		
				List<PostImages> findByPost = this.postImageRepository.findByPost(post);
				
				List<PostImageDto> postImageDto = findByPost.stream().map((posts) -> this.modelMapper.map(posts, PostImageDto.class)).collect(Collectors.toList());
				
		return postImageDto;
	}
	
	
	
	//get Post images
	@Override
	public List<String> getPostImages(Integer postId) {
		 try {
	            // Construct the path to the post folder
	            String postFolderPath = uploads +"/"+ postId;
	            File postFolder = new File(postFolderPath);
	            
	            if (postFolder.exists() && postFolder.isDirectory()) {
	            	List<File> imageFiles = Arrays.stream(Objects.requireNonNull(postFolder.listFiles()))
	                        .filter(File::isFile)
	                        .collect(Collectors.toList());

	                if (!imageFiles.isEmpty()) {
	                	
	                	 List<String> imagePaths = imageFiles.stream()
	                             .map(File::getAbsolutePath)
	                             .collect(Collectors.toList());
	                	
	                    return imagePaths;
	                } else {
	                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No image found for the post");
	                }
	            } 
	            
	            else {
	                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
	            }
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving image", e);
	        }
	    }
	
	
	
	
	//get images by postId
	@Override
	public List<File> getImageFilesByPostId(Integer postId) {
//		Post post = this.postRepository.findById(postId)
//				.orElseThrow(()-> new ResourceNotFoundException("post","postId" , postId) );
//		List<PostImages> postImagesList = this.postImageRepository.findByPost(post);
		
		
		List<PostImageDto> postImagesList = this.getPostsByPostId(postId);
		List<File> fileObjectsList  = new ArrayList<>();
		
		
		for (PostImageDto postImage : postImagesList) {
			String filename = postImage.getFilename();
		
			Path filePath = Paths.get(uploads + File.separator + filename);
			File fileObject = filePath.toFile();
			fileObjectsList.add(fileObject);
		}
		return fileObjectsList;
	}

	
	public void deletePostImages(Integer imageId) {
		//delete images by imagesId;
		
		PostImages images =this.postImageRepository.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("PostImages", "id", imageId));
		//get postId for particular image
		String imageName=images.getFilename();
		Post post= images.getPost();
		String coverImage=post.getCover();
		
		if(imageName.equals(coverImage)) {
//			int PostId = post.getPostId();
			String nextImageName=null;
			List<PostImages> nextImages=this.postImageRepository.findByPost(post);
			for(PostImages nextImage : nextImages) {
				nextImageName=nextImage.getFilename();
			}
			
			if(!nextImageName.isBlank()) {
				
				post.setCover(nextImageName);
			}
			post.setCover(nextImageName);
			
			Post updatedPost = this.postRepository.save(post);
	        this.modelMapper.map(updatedPost, PostDto.class);
		}
		
		this.postImageRepository.delete(images);		
	}
	
	@Override
	public ArrayList<ArrayList<Object>> getImagesByPostId(Integer postId){
		
		Post post = this.postRepository.findById(postId).
				orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));
		
		List<PostImages> findByPost = this.postImageRepository.findByPost(post);
		ArrayList<ArrayList<Object>> files= new ArrayList<ArrayList<Object>>();
		int i=0-1;
		if(!findByPost.isEmpty()) {
			for(PostImages postImage : findByPost) {
				i=i+1;
//					List<String> arr=new ArrayList<>();
				Integer imageId=postImage.getId();
				String fileName=postImage.getFilename();
				Post postdata= postImage.getPost();
				Integer postdataId= postdata.getPostId();
//				fileName=uploads+ "/" +postId +"/"+ fileName;
				
//				String ipAddress = "192.168.18.8";
//			    String prefixToRemove = "C:/xampp/htdocs/";
//			    String prefixToAppend = "http://" + ipAddress + "/";
//			    fileName = fileName.replace(prefixToRemove, prefixToAppend);
//					files.add(fileName);
//					files.add(imageId);
			    files.add(new ArrayList<Object>());
			    files.get(i).add(0, imageId);
			    files.get(i).add(1, fileName);
			    files.get(i).add(2, postdataId);
				
			}
//				List<PostImageDto> postImageDto = findByPost.stream().map((posts) -> this.modelMapper.map(posts, PostImageDto.class))
//						.collect(Collectors.toList());
			return files;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No image found for the post");
		}
	}
	
	}
	

