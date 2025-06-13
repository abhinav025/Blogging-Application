package com.project.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project.blog.security.CoustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CoustomUserDetailService coustomUserDetailService;
	@Bean
	public SecurityFilterChain securityFileterChain(HttpSecurity http)throws Exception{
		
		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
		
	}
	
	public void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService(coustomUserDetailService).passwordEncoder(passwordEncoder());
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
