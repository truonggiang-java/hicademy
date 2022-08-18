package com.example.lessonEnglish.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
	private String name;
	private String description;
	private String idDlfileEntry;
	private List<String> nameLesson=new ArrayList<>();
}
