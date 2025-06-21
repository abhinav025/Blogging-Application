package com.project.blog.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtTokenHelper {
	
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	private static final String SECRET = "jwtTokenKeyjwtTokenKeyjwtTokenKeyjwtTokenKey"; // must be 32+ chars
	private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
	
	// Retrieve username from JWT token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// Retrieve expiration date from JWT token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// For retrieving any information from token we need the signing key
	private Claims getAllClaimsFromToken(String token) {
		try {
	        JwtParser parser = Jwts.parserBuilder()
	            .setSigningKey(key)
	            .build();
	        return parser.parseClaimsJws(token).getBody();
	    } catch (Exception e) {
	        // Log the error or rethrow as a custom exception
	        throw new RuntimeException("Invalid JWT token", e);
	    }
	}

	// Check if the token has expired
	private boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// Generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		Date now = new Date(System.currentTimeMillis());
		Date expiry = new Date(now.getTime() + JWT_TOKEN_VALIDITY * 1000);

		return Jwts.builder()
			.setClaims(claims)
			.setSubject(subject)
			.setIssuedAt(now)
			.setExpiration(expiry)
			.signWith(key)
			.compact();
	}

	// Validate token
	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	
	
	

}
