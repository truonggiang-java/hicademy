package com.example.lessonEnglish.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lessonEnglish.dto.LessonDto;
import com.example.lessonEnglish.entity.Course;
import com.example.lessonEnglish.entity.Lesson;
import com.example.lessonEnglish.repository.CourseRepository;
import com.example.lessonEnglish.repository.LessonRepository;

@Service
public class LessonService {
	@Autowired
	private LessonRepository lessonRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	public String insertLesson(LessonDto lessonDto) {
		try {
			Lesson lesson=new Lesson();
			List<Course> courses=courseRepository.findListCourse(lessonDto.getIdCourse());
			lesson.setDescription(lessonDto.getDescription());
			lesson.setIdDlfileEntry(lessonDto.getIdDlfileEntry());
			lesson.setName(lessonDto.getName());
			lesson.setCourses(courses);
			lessonRepository.save(lesson);
			return "Bạn thêm bài học thành công";
		} catch (Exception e) {
			e.printStackTrace();
			return "Bạn thêm bài học thất bại";
			// TODO: handle exception
		}
	}
	
	public List<Lesson> findAllLesson() {
		return lessonRepository.findAll();
	}
}
