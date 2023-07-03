package com.fame.famewheels.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDto {
	
	private int paymentId;
	@NotEmpty
	private String securityDeposit;
	@NotEmpty
	private String PaymentMethod;
	private String status;
	
//	private MemberDto member;
	
	private UserDto user;
}
