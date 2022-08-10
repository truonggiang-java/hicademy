package com.example.lessonEnglish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonImageDto {
	private String id;
	private String name;
	private String description;
	private String link;
	private Integer countCourse;
	private String course;
}
