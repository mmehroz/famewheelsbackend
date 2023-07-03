package com.fame.famewheels.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Province {
	@Id
	private int provinceId;
	
	@Column(name="province_name")
	private String provinceName;
}
