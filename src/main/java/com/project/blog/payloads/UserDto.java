package com.project.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// this class is used to transfer the data!

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	
	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "Username must be of min of 4 characters !!")
	private String name;
	
	@Email(message = "Email address is not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min = 4, max = 12, message = "Password must be min of 4 chars and max of 12 chars !!")
	private String password;
	
	@NotNull
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();

}
