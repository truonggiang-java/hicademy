package com.example.lessonEnglish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearnDto {
	private String idUser;
	private String idLesson;
	private Integer point;
}
