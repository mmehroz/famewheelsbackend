package com.fame.famewheels.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostImageDto {
	
	private int id;
    
    private String filename;
    
    
    private int postId;
    private PostDto post;

}
