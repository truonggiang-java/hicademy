package com.example.lessonEnglish.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
	@NotBlank(message="Name cannot be null")
	private String name;
	private String gender;
	
	
	private String dateOfBirth;
	private String address;
	
	private String telephone;
	
	@Email(message = "Email should be valid")
	private String email;
	
	@Size(min = 5,max = 100, message = "Password must be at least 5 characters")
	private String password;
	
	@NotBlank(message="Role cannot be null")
	private String role;
}
