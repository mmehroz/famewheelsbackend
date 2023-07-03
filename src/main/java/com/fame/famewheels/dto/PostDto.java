package com.fame.famewheels.dto;



import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fame.famewheels.entities.ModelYear;
import com.fame.famewheels.entities.PostType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class PostDto {
	
	private int postId;
	
	@NotEmpty
	private String title;

	// @NotEmpty
	// private String city;
	
//	@NotEmpty
//	private String vehicleMainImage;
	private Date addedDate;
	
	@NotEmpty
	private String vehicleColour;;
	
	@NotEmpty
	private String milage;
	
	@NotNull 
	private long price;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private String phone;
	
//	@NotEmpty
//	private String make;
	
//	@NotEmpty
//	private String model;
	
	private String secondaryPhone;
	
//	@NotEmpty
//	private String year;
	
	@NotEmpty
	private String registeredIn;
	
	@NotEmpty
	private String transmission;
	
	@NotEmpty
	private String vehicleCondition;
	@NotEmpty
	private String vehicleFuel;
	
	private int status;
	
	
	private String categoryName;
	
	private String userName;
	
	private String cityName;
	private String makeName;
	private String modelName;
	private String yearName;
	private String typeName;

	private int counter;
	
	private String cover;
	
	private PostTypeDto postType;
	private CityDto city;
	private ModelYearDto year;
	private MakeDto make;
	private ModelDto model;
	private CategoryDto category;
	private UserDto user;
	private PostImageDto postImage;
	
}
