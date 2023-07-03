package com.fame.famewheels.entities;
import jakarta.persistence.Column;
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
public class VehicleInspection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inspectionId;
	@Column(nullable = false)
	private String fullName;
	@Column(nullable = false)
	private String vehicleType;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String inspectionSlot;
	@Column(nullable = false)
	private String phone;
	private String description;
	@Column(nullable = false)
	private String city;
	
	@ManyToOne
	private User user;
}
