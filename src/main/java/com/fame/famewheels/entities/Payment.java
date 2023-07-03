package com.fame.famewheels.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	
	@Column(nullable = false)
	private String securityDeposit;
	
	@Column(nullable = false)
	private String PaymentMethod;
	
	@Column
	private String status;
	
//	@ManyToOne
//	@JoinColumn(name="member_id")
//	private Member member;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
}
