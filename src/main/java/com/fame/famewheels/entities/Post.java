package com.fame.famewheels.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	
	@Column(nullable = false, name="post_title")
	private String title;

	// @Column(nullable=false)
	// private String city;

//	@Column
//	private String vehicleMainImage;
	
	private Date addedDate;
	
	@Column(name="vehicle_colour")
	private String vehicleColour;
	
	@Column(nullable=false)
	private String milage;
	
	@NotNull 
	private long price;
	
	@Column(nullable=false)
	private String phone;
	
	private String secondaryPhone;
	
	@Column(length= 1000)
	private String description;
	
//	@Column(nullable=false)
//	private String make;
	
//	@Column(nullable=false)
//	private String model;
	
//	@Column(nullable = false)
//	private String year;
	
	@Column(nullable = false)
	private String registeredIn;
	
	@Column(nullable = false)
	private String transmission;
	
	@Column(nullable = false)
	private String vehicleCondition;
	
	@Column(nullable = false)
	private String vehicleFuel;
	
	private int counter;
	
	@ManyToOne
	@JoinColumn(name="post_type_id")
	private PostType postType;
	
	
	private String cover;
	
	@Column(columnDefinition="integer default 1")
	private int status;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;
	
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	@JoinColumn(name="make_id")
	private Make make;
	
	@ManyToOne
	@JoinColumn(name="model_id")
	private Model model;
	
	@ManyToOne
	@JoinColumn(name="year_id")
	private ModelYear modelYear;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImages> post = new ArrayList<>();
	

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuctionPost> auctionPost = new ArrayList<>();
//	
	// @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
	// @JoinColumn(name="city_id")
	// private Cities cities;
	
	

}
