package com.example.lessonEnglish.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {
	@NotBlank(message="Name cannot be null")
	private String name;
	
	@NotBlank(message="Image cannot be null")
	private String idDlfileEntry;
	
	private String description;
	private List<String> nameCourse=new ArrayList<>();
	
}
