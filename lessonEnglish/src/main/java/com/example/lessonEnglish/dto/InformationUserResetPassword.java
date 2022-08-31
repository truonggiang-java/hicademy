package com.example.lessonEnglish.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformationUserResetPassword {
	private String name;
	private String email;
	private String telephone;
	private String dateBirthDay;
}
