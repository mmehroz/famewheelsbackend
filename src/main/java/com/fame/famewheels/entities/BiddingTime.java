package com.fame.famewheels.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BiddingTime {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int biddingTimeId;
	
//	@Temporal(TemporalType.TIME)
	private String startTime;
//	@Temporal(TemporalType.TIME)
	private String EndTime;

}
