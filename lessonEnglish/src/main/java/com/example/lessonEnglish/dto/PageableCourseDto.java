package com.example.lessonEnglish.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageableCourseDto {
	private List<CourseImageDto> courseImage=new ArrayList<>();
	private PageDto page;
}
