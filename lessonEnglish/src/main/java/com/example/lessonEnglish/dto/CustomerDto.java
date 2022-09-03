package com.example.lessonEnglish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
	private String name;
	private String gender;
	private String dateOfBirth;
	private String address;
	private String telephone;
	private String email;
	private String password;
}
