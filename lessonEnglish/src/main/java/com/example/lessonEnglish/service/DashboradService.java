package com.example.lessonEnglish.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lessonEnglish.dto.DashboradDto;
import com.example.lessonEnglish.repository.CourseRepository;
import com.example.lessonEnglish.repository.DlFileEntryRepository;
import com.example.lessonEnglish.repository.LessonRepository;
import com.example.lessonEnglish.repository.VideoRepository;

@Service
public class DashboradService {
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private LessonRepository lessonRepository;
	
	@Autowired
	private DlFileEntryRepository dlFileEntryRepository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	public List<DashboradDto> dashborad(){
		List<DashboradDto> listDashboradDtos=new ArrayList<>();
		listDashboradDtos.add(new DashboradDto(courseRepository.count(), "Course"));
		listDashboradDtos.add(new DashboradDto(lessonRepository.count(), "Lesson"));
		listDashboradDtos.add(new DashboradDto(dlFileEntryRepository.count(), "Image"));
		listDashboradDtos.add(new DashboradDto(videoRepository.count(), "Video"));
		return listDashboradDtos;
	}
}
