package com.example.lessonEnglish.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.example.lessonEnglish.dto.DlFileEntryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiniLessonRequest {
	private String id;
	private String name;
	private String description;
	private List<DlFileEntryDto> listDlFileEntryDto=new ArrayList<>();
}
