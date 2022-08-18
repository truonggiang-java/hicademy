package com.example.lessonEnglish.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {
	private String name;
	private String idDlfileEntry;
	private String description;
	private List<String> nameCourse=new ArrayList<>();
	
}
