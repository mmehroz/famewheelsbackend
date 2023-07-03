package com.fame.famewheels.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vehicles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vehicle_id;
	
	private String model;
	
	private String type;
	private int price;
	private String description;
	private String image;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@OneToOne
	@JoinColumn(name="post_id")
	private Post post;
	

}
