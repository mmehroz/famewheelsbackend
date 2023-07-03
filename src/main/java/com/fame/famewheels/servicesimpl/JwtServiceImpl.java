package com.fame.famewheels.servicesimpl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fame.famewheels.entities.User;
import com.fame.famewheels.repositories.UserRepository;
import com.fame.famewheels.services.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService{
	@Autowired
	private UserRepository userRepository;
	
	
	
	public static final String SECRET = "3F4428472B4B6250655368566D597133743677397A244326452948404D635166";
	

	@Override
	public String extractUserNameFromToken(String token) {
		
		return extractClaim(token, Claims::getSubject);
	}

	@Override
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	@Override
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims =extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	@Override
	public Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	@Override
	public Boolean isTokenExpire(String token) {
		
		return extractExpiration(token).before(new Date());
	}

	@Override
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUserNameFromToken(token);
	    return (userName.equals(userDetails.getUsername()) && !isTokenExpire(token));
	}

	@Override
	public String generateToken(String userName) {		
		Map<String, Object> claims=new HashMap<>();	
		User userDetail = this.userRepository.findByEmail(userName).get();
		if(userDetail != null) {			
//			User user = this.userRepository.findById(userDetail.getId()).get();
			claims.put("id", userDetail.getId());
//			claims.put("username", user.getUserName());
			claims.put("phone", userDetail.getPhone());
//			claims.put("userName", userDetail.getUsername());
		}
		return createToken(claims,userName);
	}

	private String createToken(Map<String, Object> claims, String userName) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*12))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}
	
	private Key getSignKey()
	{
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	
}
