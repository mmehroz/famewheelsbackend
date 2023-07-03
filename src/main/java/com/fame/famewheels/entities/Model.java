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
@Getter
@Setter
@NoArgsConstructor
public class Model {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int modelId;
	@Column(nullable=false, length=100)
	private String modelName;
	
	@ManyToOne
	@JoinColumn(name="make_id")
	private Make make;
	
	@OneToMany(mappedBy="model", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Post> post =new ArrayList<>();
}
