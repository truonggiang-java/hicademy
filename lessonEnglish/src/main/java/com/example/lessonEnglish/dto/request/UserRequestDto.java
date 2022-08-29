package com.example.lessonEnglish.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
	private String idLogo;
	private String name;
	private String phoneNumber;
	private String address;
	private String date;
	private String role;
	private String gender;
}
