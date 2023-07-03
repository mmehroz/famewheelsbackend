package com.fame.famewheels.dto;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BiddingTimeDto {

private int biddingTimeId;
//	@Temporal(TemporalType.TIME)
	private String startTime;
//	@Temporal(TemporalType.TIME)
	private String EndTime;
}
