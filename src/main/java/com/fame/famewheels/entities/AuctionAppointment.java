package com.fame.famewheels.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class AuctionAppointment {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int appointmentId;
	
//	@Temporal(TemporalType.DATE)
	private String appointmentDate;
	
//	@Temporal(TemporalType.TIME)
	private String appointmentTime;
	
	@Column(length=50)
	private String userName;
	
	private int contactNo;
	@Column(length=100)
	private String email;
	
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private Branch branch;
	
	

}
