package com.foodbox.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
	
		private String secretKey = "VirtualProton";
		
		public String extractUsername(String token) {
			return extractClaim(token, Claims::getSubject);
		}
		public Date extractExpiration(String token) {
			return extractClaim(token,Claims::getExpiration);
		}
		
		public <T> T extractClaim(String Token, Function<Claims, T> claimsResolver) {
			final Claims claims= extractAllClaims(Token);
			return claimsResolver.apply(claims);
			
		}
		private Claims extractAllClaims(String token) {
			// TODO Auto-generated method stub
			return  Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		}
		private Boolean isTokenExpired(String token) {
			return extractExpiration(token).before(new Date());
		}
		public String generateToken(String username) {
			Map<String, Object> claims = new HashMap<>();
			return createToken(claims,username);
		}

		private String createToken(Map<String, Object> claims, String subject) {
			// TODO Auto-generated method stub
			return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
	                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
		}
		
		public Boolean validateToekn(String token, UserDetails userDetails) {
			final String username = extractUsername(token);
			return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
		}
}