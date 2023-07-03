package com.fame.famewheels.services;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {
	 
	//generate token
	public String generateToken(String userName);
	
	//extract userName from Token
	public String extractUserNameFromToken(String token);
	
	//extract expiration date
	public Date extractExpiration(String token);
	
	//extract Token claim
	public <T> T extractClaim(String token, Function<Claims,T> claimsResolver);
	
	//extract all claims
	public Claims extractAllClaims(String token);
	
	//check token expiry
	public Boolean isTokenExpire(String token);
	
	//token validation
	public Boolean validateToken(String token,UserDetails userDetails);
	
	
	
}
