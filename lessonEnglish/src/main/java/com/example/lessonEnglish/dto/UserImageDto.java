package com.example.lessonEnglish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserImageDto {
	private String id;
	private String idLogo;
	private String name;
	private String gender;
	private String email;
	private String role;
	private String date;
	private String link;
	private String phone;
	private String address;
}
