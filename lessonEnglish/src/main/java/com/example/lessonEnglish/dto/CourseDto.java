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
public class CourseDto {
	@NotBlank(message = "Tên không được phép để trống")
	private String name;
	private String description;
	
	@NotBlank(message="Không có ảnh đại diện khóa học đó")
	private String idDlfileEntry;
	
	private String param;
	private List<String> nameLesson=new ArrayList<>();
}
