package com.fame.famewheels.dto;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min=4, message = "Username must be min of 4 characters")
	private String userName;
	
	@Email(message = "Email address is not valid ")
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String phone;
	private String CNIC;
	private String Address;
	private String udid;
	private int isAuctioneer;
	
	private String roleName;
	
	private RoleDto role;
	
}
