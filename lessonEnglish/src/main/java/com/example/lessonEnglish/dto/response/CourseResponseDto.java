package com.example.lessonEnglish.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {
	private String id;
	private String name;
	private String description;
	private String idDlfileEntry;
	private Integer count;
	
	
}
