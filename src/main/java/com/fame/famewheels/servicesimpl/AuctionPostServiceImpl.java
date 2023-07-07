package com.fame.famewheels.servicesimpl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fame.famewheels.controllers.AuctionPostController;
import com.fame.famewheels.dto.AuctionPostDto;
import com.fame.famewheels.dto.PostDto;
import com.fame.famewheels.dto.UserDto;
import com.fame.famewheels.entities.AuctionPost;
import com.fame.famewheels.entities.Post;
import com.fame.famewheels.entities.User;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.repositories.AuctionPostRepository;
import com.fame.famewheels.repositories.PostRepository;
import com.fame.famewheels.repositories.UserRepository;
import com.fame.famewheels.services.AuctionPostService;
import com.fame.famewheels.services.PostService;
import com.fame.famewheels.services.UserService;
import java.util.HashMap;

@Service
public class AuctionPostServiceImpl implements AuctionPostService {

	
    Logger logger = LoggerFactory.getLogger(AuctionPostServiceImpl.class);
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private AuctionPostRepository auctionPostRepository;
	@Autowired
	private ModelMapper modelMapper;
//	
	@Override
	public AuctionPostDto createAuctionPost(AuctionPostDto auctionPost, UserDto user, PostDto post) {
		 
		 User userdata=this.userRepository.findById(user.getId()).
				 orElseThrow(() -> new ResourceNotFoundException("User", "id", user.getId()));
		 
		 Post postdata=this.postRepository.findById(post.getPostId()).
				 orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", post.getPostId()) );
		 AuctionPost aucPost=this.modelMapper.map(auctionPost, AuctionPost.class);		
//		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
//		   LocalDateTime now = LocalDateTime.now();
			SimpleDateFormat dtf = new SimpleDateFormat("HH:mm:ss");  

		 Date dnow= new Date();
		 
		 
		 aucPost.setUser(userdata);
		 aucPost.setPost(postdata);
		 aucPost.setStatus(1);
//		 aucPost.setAuctionDate(new Date());
//		 aucPost.setAuctionStartTime(dtf.format(dnow));
//		 aucPost.setAuctionEndTime(dtf.format(dnow));
		 
		 try {
			 AuctionPost auction=this.auctionPostRepository.save(aucPost);
		     return this.modelMapper.map(auction, AuctionPostDto.class);
		 }catch (Exception e) {
		        // Handle any database-related exceptions here
		        throw new RuntimeException("Failed to create post. Please try again.", e);
		    }
		
	}
	@Override
	public List<AuctionPostDto> getUpcomingPost(int pageNo, int pageSize) {
		
		SimpleDateFormat dtf = new SimpleDateFormat("HH:mm:ss"); 
		
		SimpleDateFormat ndate=new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date(); 
	    logger.info(ndate.format(date));
	    logger.info(dtf.format(date));
	    
	    Pageable page=PageRequest.of(pageNo, pageSize, Sort.by("auction_date").ascending());
	    
	    Page<AuctionPost> pagination= this.auctionPostRepository.findUpcoming(ndate.format(date), dtf.format(date), page);
		List<AuctionPost> getPost= pagination.getContent();
		List<AuctionPostDto> Post=getPost.stream().map((p)-> this.modelMapper.map(p, AuctionPostDto.class)).collect(Collectors.toList());

		return Post;
	}
	
	public List<AuctionPostDto> currentAuction(){
		SimpleDateFormat ndate=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dtf= new SimpleDateFormat("HH:mm:ss");
		Date date= new Date();
		List<AuctionPost> getPost=this.auctionPostRepository.findCurrentPost(ndate.format(date), dtf.format(date));
		List<AuctionPostDto> post=getPost.stream().map((p)-> this.modelMapper.map(p, AuctionPostDto.class)).collect(Collectors.toList());
		return post;
	}
	@Override
	public AuctionPostDto getAuctionPostById(int auctionPostId) {
		AuctionPost getById =this.auctionPostRepository.findById(auctionPostId).
				orElseThrow(() -> new ResourceNotFoundException("AuctionPost", "AuctionPostId", auctionPostId));
		return this.modelMapper.map(getById, AuctionPostDto.class);
	}
	@Override
	public int getPostCountForToday(String date, String time) {
//		SimpleDateFormat ndate=new SimpleDateFormat("yyyy-MM-dd");
//		Date getDate=new Date();
		int PostCount=this.postRepository.getPostCountForToday(date, time);
		return PostCount;
	}
	@Override
	public Map<String, Integer> getPostCount() {
		SimpleDateFormat ndate=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dtf= new SimpleDateFormat("HH:mm:ss");
		Date date= new Date();
		int getUpcomingPostCount= this.auctionPostRepository.getUpcomingPostCount(ndate.format(date), dtf.format(date));
		int getCurrentPost =this.auctionPostRepository.getCurrentPostCount(ndate.format(date), dtf.format(date));
		Map<String, Integer> count=new HashMap<>();
		count.put("UpcomingPost",getUpcomingPostCount);
		count.put("CurrentPost", getCurrentPost);
		return count;
		
	}

}
