package com.fame.famewheels.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String userName;
	@Column(nullable=false, unique=true)
	private String email;
	private String password;
	@Column(nullable=false)
	private String CNIC;
	private String Address;
	@Column(nullable=false)
	private String phone;
	private String udid;
	@Column(columnDefinition="Integer default 0")
	private int isAuctioneer;
	
	@OneToMany(mappedBy = "user" ,  fetch = FetchType.LAZY)
	private List<Post> post = new ArrayList<>();
	
	@OneToMany(mappedBy = "user" ,  fetch = FetchType.LAZY)
	private List<Vehicles> vehicle = new ArrayList<>();
	
	@OneToMany(mappedBy = "user" ,  fetch = FetchType.LAZY)
	private List<ServicesOffered> services = new ArrayList<>();
	
	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
	private List<VehicleInspection> vehicleInspections  = new ArrayList<>();
	
	
	@OneToMany(mappedBy ="user", fetch = FetchType.LAZY)
	private List<AuctionPost> auctionPost=new ArrayList<>();
	
//	@OneToOne(mappedBy="user", fetch= FetchType.LAZY)
//	private Member member;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Payment> payment=new ArrayList<>();
	
	@OneToMany(mappedBy ="user", fetch = FetchType.LAZY)
	private List<Bid> bid=new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="Role_id")
	private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	

}
