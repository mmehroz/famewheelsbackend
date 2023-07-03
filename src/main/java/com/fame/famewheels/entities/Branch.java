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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Branch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int branchId;
	@Column(length=50)
	private String branchName;
	
	@Column(length=50)
	private String location;
	@Column(columnDefinition="integer default 1")
	private int status;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;
	
	@OneToMany(mappedBy="branch", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<AuctionAppointment> appointment= new ArrayList<>();
	
	

}
