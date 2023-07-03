package com.fame.famewheels.servicesimpl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.PostDto;
import com.fame.famewheels.dto.PostImageDto;
import com.fame.famewheels.entities.Category;
import com.fame.famewheels.entities.City;
import com.fame.famewheels.entities.Make;
import com.fame.famewheels.entities.Model;
import com.fame.famewheels.entities.ModelYear;
import com.fame.famewheels.entities.Post;
import com.fame.famewheels.entities.PostImages;
import com.fame.famewheels.entities.PostType;
import com.fame.famewheels.entities.User;
import com.fame.famewheels.exceptions.DataIntegrityViolationException;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.repositories.CategoryRepository;
import com.fame.famewheels.repositories.CityRepository;
import com.fame.famewheels.repositories.MakeRepository;
import com.fame.famewheels.repositories.ModelRepository;
import com.fame.famewheels.repositories.ModelYearRepository;
import com.fame.famewheels.repositories.PostImageRepository;
import com.fame.famewheels.repositories.PostRepository;
import com.fame.famewheels.repositories.PostTypeRepository;
import com.fame.famewheels.repositories.UserRepository;
import com.fame.famewheels.services.PostService;


@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	@Lazy
	private PostService postService;

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private PostImageRepository postImageRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private MakeRepository makeRepository;
	@Autowired
	private ModelRepository modelRepository;
	
	@Autowired
	private ModelYearRepository modelYearRepository;
	
	@Autowired
	private PostTypeRepository postTypeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	//create post
	
	@Override
	public PostDto createPost(PostDto postDto) 
	{
		User user = this.userRepository.findByEmail(postDto.getUserName())
		.orElseThrow(() -> new ResourceNotFoundException("User", "UserName", postDto.getUserName()));
		
		PostType type= this.postTypeRepository.findByTypeName(postDto.getTypeName());
		
			
		Category category = this.categoryRepository.findByCategoryName(postDto.getCategoryName());

		City city=this.cityRepository.findByCityName(postDto.getCityName());
		 
		Make make= this.makeRepository.findByMakeName(postDto.getMakeName());
		
		Model model =this.modelRepository.findByModelName(postDto.getModelName());
		
		ModelYear year= this.modelYearRepository.findByYearName(postDto.getYearName());
//		if(type==null) {
//			throw new ResourceNotFoundException("PostType", "TypeName", "free Ads");
//		}
		if(type==null) {
			throw new ResourceNotFoundException("PostType", "TypeName", postDto.getTypeName());
		}
		if(year==null) {
			throw new ResourceNotFoundException("ModelYear", "YearName", postDto.getYearName());
		}
		if(model==null) {
			throw new ResourceNotFoundException("Model", "ModelName", postDto.getModelName());
		}
		
		if(make==null) {
			throw new ResourceNotFoundException("Make", "MakeName", postDto.getMakeName());
		}

		if(city==null){

			throw new ResourceNotFoundException("City", "CityName", postDto.getCityName());
		}
		if (category == null)
			{
			 throw new ResourceNotFoundException("Category", "CategoryName", postDto.getCategoryName());
		 	}
		
		
		Post post = this.modelMapper.map(postDto, Post.class);
//		post.setVehicleMainImage(postDto.getVehicleMainImage()); 
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		post.setCity(city);
		post.setMake(make);
		post.setModel(model);
		post.setModelYear(year);
		post.setStatus(1);
		post.setPostType(type);
		
		try {
	        Post savedPost = this.postRepository.save(post);
	        return this.modelMapper.map(savedPost, PostDto.class);
	    }catch (Exception e) {
	        // Handle any database-related exceptions here
	        throw new RuntimeException("Failed to create post. Please try again.", e);
	    }
//		Post savedPost = this.postRepository.save(post);
//		return this.modelMapper.map(savedPost, PostDto.class);
	}
	

	
	
	//updatePost 
	
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		
		
//		String cover=post.getCover();
		
		City city=this.cityRepository.findByCityName(postDto.getCityName());
		Category category=this.categoryRepository.findByCategoryName(postDto.getCategoryName());
		Make make=this.makeRepository.findByMakeName(postDto.getMakeName());
		Model model=this.modelRepository.findByModelName(postDto.getModelName());
		ModelYear year=this.modelYearRepository.findByYearName(postDto.getYearName());
		
		if(year==null) {
			throw new ResourceNotFoundException("ModelYear", "YearName", postDto.getYearName());
		}
		if(model==null) {
			throw new ResourceNotFoundException("Model", "ModelName", postDto.getModelName());
		}
		
		if(make==null) {
			throw new ResourceNotFoundException("Make", "MakeName", postDto.getMakeName());
		}
		
		if(city==null){
			
			throw new ResourceNotFoundException("City", "CityName", postDto.getCityName());
		}
		if (category == null)
		{
			throw new ResourceNotFoundException("Category", "CategoryName", postDto.getCategoryName());
		}
		post.setTitle(postDto.getTitle());
		post.setVehicleColour(postDto.getVehicleColour());
		post.setMilage(postDto.getMilage());
		// post.setCity(postDto.getCity());
		post.setPrice(postDto.getPrice());
		post.setPhone(postDto.getPhone());
		post.setRegisteredIn(postDto.getRegisteredIn());
		post.setVehicleCondition(postDto.getVehicleCondition());
		post.setTransmission(postDto.getTransmission());
		post.setVehicleFuel(postDto.getVehicleFuel());
		post.setCounter(postDto.getCounter());
//		post.setCover(postDto.getCover());		
		post.setDescription(postDto.getDescription());
		post.setSecondaryPhone(postDto.getSecondaryPhone());
		post.setCity(city);
		post.setCategory(category);
		post.setMake(make);
		post.setModel(model);
		post.setModelYear(year);
	
		try {
	        Post updatedPost = this.postRepository.save(post);
	        return this.modelMapper.map(updatedPost, PostDto.class);
	    } catch (Exception e) {
	        // Handle any database-related exceptions here
	        throw new RuntimeException("Failed to create post. Please try again.", e);
	    }
		
		
	}

	@Override
	public void deletePost(Integer postId) {
		
		//update the status column only
		Post post= this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		
		post.setStatus(0);
		this.postRepository.save(post);
		
//		Post post = this.postRepository.findById(postId)
//		.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
//		this.postRepository.delete(post);
		
	}
	
	//get post by post id.
	@Override
	public PostDto getPostById(Integer postId) {
//		PostType type=this.postTypeRepository.findById(typeId).orElseThrow(()-> new ResourceNotFoundException("PostType", "PostTypeId", typeId));
		Post post = this.postRepository.findBypostIdAndStatus(postId, 1);
		if(post==null) {
			throw  new ResourceNotFoundException("Post", "postId", postId);	
		}
		
//		List<PostImageDto> images= this.postImageRepository.getByPost(post);
				
		return this.modelMapper.map(post, PostDto.class);
	}
	
	//get all posts
	@Override
	public List<PostDto> getAllPosts(Integer pageNo, Integer pageSize) {
		
		
		Pageable p= PageRequest.of(pageNo, pageSize, Sort.by("postId").descending());
		
		Page<Post> posts = this.postRepository.findByStatus(1, p);
		
//		 Sort the list in descending order based on addedDate field
	    
	    List<Post> post= posts.getContent();
//	    Post.sort(Comparator.comparing(Post::getAddedDate).reversed());
	    
//	    post.sort(Comparator.comparing(Post::getAddedDate).reversed());
		
		
		List<PostDto> allPosts = post.stream().map((po) ->
		{
		PostDto postDto = this.modelMapper.map(po, PostDto.class);
		PostImages postImage=	this.postImageRepository.findById(postDto.getPostId()).orElse(null);
		if (postImage != null) {
            PostImageDto postImageDto = this.modelMapper.map(postImage, PostImageDto.class);
            postDto.setPostImage(postImageDto);
        }
		return postDto;
		}).collect(Collectors.toList());
		return allPosts;
		
	}

	//get posts by category id
	@Override
	public List<PostDto> getPostsByCategoryId(Integer categoryId) {
		Category cat =  this.categoryRepository.findById(categoryId).
		orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", categoryId));
		
		List<Post> categoryPosts = this.postRepository.findByCategoryAndStatus(cat, 1);
		List<PostDto> catPosts = categoryPosts.stream().map((posts) -> this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		
		return catPosts;
	}
	
	//get posts by user id
		@Override
		public List<PostDto> getPostsByUserId(Integer userId) {
			User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
			List<Post> userPosts = this.postRepository.findByUserAndStatus(user, 1);
			List<PostDto> posts = userPosts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
			return posts;
		}
		
		
	//get posts by category name	
		@Override
		public List<PostDto> getPostsByCategoryName(String categoryName) {
			Category category = this.categoryRepository.findByCategoryName(categoryName);
			List<Post> categoryPosts = this.postRepository.findByCategoryAndStatus(category, 1);
			List<PostDto> collect = categoryPosts.stream().map((categor)->this.modelMapper.map(categor, PostDto.class)).collect(Collectors.toList());			
			return collect;
		}
		
		
	
	//get posts by city 

	// @Override
	// public List<PostDto> getPostsByCity(String city) {
	// 	List<Post> posts =this.postRepository.findByCity(city);
	// 	List<PostDto> postsByCity = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	// 	return postsByCity;
	// }
	
	
	//get posts by care make (example toyota, Honda)

	@Override
	public List<PostDto> getPostsByMake(String make) {
		List<Post> makes = this.postRepository.findByMakeAndStatus(make, 1);
		List<PostDto> postByMake = makes.stream().map((mak)->this.modelMapper.map(mak, PostDto.class)).collect(Collectors.toList());
		return postByMake;
	}

	//get Posts By Model

	@Override
	public List<PostDto> getPostsByModel(String model) {
		List<Post> byModel = this.postRepository.findByModelAndStatus(model, 1);
		List<PostDto> byMod = byModel.stream().map((mod)->this.modelMapper.map(mod, PostDto.class)).collect(Collectors.toList());
		return byMod;
	}


	
	
	//api for post cover image url
	@Override
	public String postCover(Integer postId, String coverImageUrl) {
		Post post = this.postRepository.findByPostIdAndStatus(postId, 1);
		if(post==null) {
			throw new ResourceNotFoundException("Post","postId",postId);	
		}
		
		post.setCover(coverImageUrl);
		this.postRepository.save(post);
		return "Post Cover Updated";
	}




	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		
		
		Pageable page=PageRequest.of(0, 3, Sort.by("post_id").descending());
		
		Page<Post> p = this.postRepository.searchPost(keyword, page);
		List<Post> search = p.getContent();		
		List<PostDto> searchKeyword= search.stream().map((s) -> this.modelMapper.map(s, PostDto.class)).collect(Collectors.toList());
		return searchKeyword;
		
	}
	
	@Override
	public List<PostDto> vehicleType(Integer typeId, Integer pageNo, Integer pageSize) {
		Pageable page=PageRequest.of(pageNo, pageSize, Sort.by("post_id").descending());
		
		Page<Post> byType = this.postRepository.getByTypeAndStatus(typeId, page);
		
		List<Post> getByType = byType.getContent();
		List<PostDto> byPostType = getByType.stream().map((typ)->this.modelMapper.map(typ, PostDto.class)).collect(Collectors.toList());
		return byPostType;
	}
	
	//get posts by vehicle condition (only free and featured ads)
	@Override
	public List<PostDto> vehicleCondition(String condition, Integer pageNo, Integer pageSize) {
		
		Pageable page= PageRequest.of(pageNo, pageSize, Sort.by("post_id").descending());
		
		Page<Post> p= this.postRepository.findByVehicleConditionAndStatus(condition, page);
		List<Post> byCondition = p.getContent();
		List<PostDto> byCond = byCondition.stream().map((con)->this.modelMapper.map(con, PostDto.class)).collect(Collectors.toList());
		return byCond;
	}

	@Override
	public List<PostDto> filters(String vehicleCondition, Integer city, Integer make, Integer minPrice, Integer maxPrice, Integer
	category, String keyword, Integer pageNo, Integer pageSize) {
		
		
		Pageable p= PageRequest.of(pageNo, pageSize, Sort.by("post_id").descending());
		
		if(keyword.equals("null")) {
			Page<Post> filter=this.postRepository.filterpost(vehicleCondition, city, make, minPrice, maxPrice, category, p);
			List<Post> post= filter.getContent();
			List<PostDto> filt= post.stream().map((fil)->this.modelMapper.map(fil, PostDto.class)).collect(Collectors.toList());				
			
			return filt;
			
			
		}else {
			Page<Post> page = this.postRepository.searchPost(keyword, p);
			List<Post> search = page.getContent();		
			List<PostDto> searchKeyword= search.stream().map((s) -> this.modelMapper.map(s, PostDto.class)).collect(Collectors.toList());
			return searchKeyword;
		}
		
		
//		 Sort the list in descending order based on addedDate field
	    

//		if(city==null && make==null && minPrice==0 && maxPrice==100000000) {
//			List<Post> filter =this.postRepository.findByVehicleCondition("New");
//			List<PostDto> filt= filter.stream().map((fil)->this.modelMapper.map(fil, PostDto.class)).collect(Collectors.toList());
//			return filt;
//			
//		}else {
	
	// if(city==null){
		// city=postRepository.getcities();
		// city=List.of("karachi",  "faisalabad");
		// city=CityDto.getCityName();
		// filter =this.postRepository.filterpost(vehicleCondition, city, make, minPrice, maxPrice);
		// filt= filter.stream().map((fil)->this.modelMapper.map(fil, PostDto.class)).collect(Collectors.toList());		
		

	// }
	// if(make==null){
		// make="('honda', 'toyota', 'BMW')";
		// filter =this.postRepository.filterpost(vehicleCondition, city, make, minPrice, maxPrice);
		// filt= filter.stream().map((fil)->this.modelMapper.map(fil, PostDto.class)).collect(Collectors.toList());		
		
		
	// }
	
	
	
			
		}
	}
	


