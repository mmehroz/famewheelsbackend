package com.fame.famewheels.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VehicleServiceAssistant {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		@Column(nullable = false)
		private String serviceAvail;
		
		@OneToMany(mappedBy ="vehicleServiceAssistant" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		private List<ServicesOffered> servicesOffered = new ArrayList<>();
}
