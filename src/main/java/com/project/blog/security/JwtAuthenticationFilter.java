package com.project.blog.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		

		// 1- get token
		
		String requestToken = request.getHeader("Authorization");
		
		// bearer 235hoa (token format)
		
		System.out.println(requestToken);
		
		String username = null;
		
		String token = null;
		
		if(request!=null && requestToken.startsWith("Bearer")) {
			
			token = requestToken.substring(7);
			
			try {
				username = this.jwtTokenHelper.getUsernameFromToken(token);
			}
			catch(IllegalArgumentException e){
				System.out.println("unable to get jwt token");
			}
			catch(ExpiredJwtException e) {
				System.out.println("Jwt token has expired");
			}
			catch(MalformedJwtException e) {
				System.out.println("Invalit Jwt");
			}
			
		}
		else {
			System.out.println("JWT token does not began with Bearer");
		}
		
		
		
		
	}

}
