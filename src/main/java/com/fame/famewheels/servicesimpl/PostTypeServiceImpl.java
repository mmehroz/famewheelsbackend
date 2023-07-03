package com.fame.famewheels.servicesimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fame.famewheels.dto.PostTypeDto;
import com.fame.famewheels.entities.PostType;
import com.fame.famewheels.exceptions.ResourceNotFoundException;
import com.fame.famewheels.repositories.PostTypeRepository;
import com.fame.famewheels.services.PostTypeService;

@Service
public class PostTypeServiceImpl implements PostTypeService {
	
	@Autowired
	private PostTypeRepository postTypeRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PostTypeDto getPostType(Integer id) {
		PostType type = this.postTypeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("PostType", "postTypeId", 1));	
		
		
//		List<PostImageDto> images= this.postImageRepository.getByPost(post);
				
		return this.modelMapper.map(type, PostTypeDto.class);
	}

	
}
