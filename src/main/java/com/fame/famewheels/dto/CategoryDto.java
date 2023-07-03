package com.fame.famewheels.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	private int categoryId;
	
	@NotBlank
	@Size(min=2 , max = 20)
	private String category;
	private String image_name;
	

}
