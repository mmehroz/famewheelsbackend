package com.fame.famewheels.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ServicesOffered {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int service_id;
	
	private String vehicle_type;
	private String vehicle_model;
	private String location;
	
	@ManyToOne
	private User user;
	@ManyToOne
	private VehicleServiceAssistant vehicleServiceAssistant;
	
	

}
