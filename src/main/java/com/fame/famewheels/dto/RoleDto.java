package com.fame.famewheels.dto;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class RoleDto {
	
	
	private int id;
	private String roleName;
	
	

}
