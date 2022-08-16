package com.example.lessonEnglish.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.lessonEnglish.dto.CourseDto;
import com.example.lessonEnglish.dto.CourseImageDto;
import com.example.lessonEnglish.dto.PageDto;
import com.example.lessonEnglish.dto.PageableCourseDto;
import com.example.lessonEnglish.entity.Course;
import com.example.lessonEnglish.entity.Lesson;
import com.example.lessonEnglish.projections.CourseProjection;
import com.example.lessonEnglish.projections.LessonCourseProjection;
import com.example.lessonEnglish.repository.CourseRepository;
import com.example.lessonEnglish.repository.LessonRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private LessonRepository lessonRepository;

	public String insertCourse(CourseDto courseDto) {
		try {
			Course course = new Course();
			List<Lesson> lessons = lessonRepository.findListLesson(courseDto.getIdLesson());
			course.setName(courseDto.getName());
			course.setDescription(courseDto.getDescription());
			course.setIdDlFileEntry(courseDto.getIdDlfileEntry());
			if (lessons.size() > 0) {
				course.setLessons(lessons);
			}
			courseRepository.save(course);
			return "Bạn đã thêm khóa học " + courseDto.getName() + " thành công";
		} catch (Exception e) {
			e.printStackTrace();
			return "Thêm khóa học thất bại";
		}
	}

	public String updateCourse(CourseDto courseDto, String id) {

		Course course = courseRepository.findById(id).get();
		List<Lesson> lessons = lessonRepository.findListLesson(courseDto.getIdLesson());
		course.setName(courseDto.getName());
		course.setDescription(courseDto.getDescription());
		course.setIdDlFileEntry(courseDto.getIdDlfileEntry());
		course.setLessons(lessons);
		courseRepository.save(course);
		return "Bạn đã cập nhật khóa học thành công";
	}

	public PageableCourseDto findAll(Integer page, Integer size, String input, String sort) {
		PageableCourseDto pageableCourseDto = new PageableCourseDto();
		Long listCourse = courseRepository.countCourse(input);
		List<CourseProjection> pageCourse = courseRepository.findAllCourse(input, (page - 1) * size, size);
		List<CourseImageDto> listImage = new ArrayList<>();
		for (CourseProjection course : pageCourse) {
			CourseImageDto courseImageDto = new CourseImageDto();
			courseImageDto.setDescription(course.getDescription());
			courseImageDto.setId(course.getId());
			courseImageDto.setName(course.getName());
			String fileName = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/api/v1/dlFileEntry/viewImage/").path(course.getIdDlFileEntry()).toUriString();
			courseImageDto.setLink(fileName);
			courseImageDto.setCountLesson(course.getCountLesson());

			// lesson
			List<LessonCourseProjection> listLesson = courseRepository.findLessonByIdCourse(course.getId());
			String lesson = "";
			if (listLesson.size() > 0) {
				for (int i = 0; i < listLesson.size(); i++) {
					if (i < listLesson.size() - 1) {
						lesson += listLesson.get(i).getName() + ", ";
					} else {
						lesson += listLesson.get(i).getName();
					}
				}
			} else {
				lesson = "Không có bài học nào trong khóa học này";
			}
			courseImageDto.setLesson(lesson);
			listImage.add(courseImageDto);
		}

		if (sort != null) {
			switch (sort) {
			case "name":
				listImage = listImage.stream().sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName())).collect(Collectors.toList());
				break;

			case "description":
				listImage = listImage.stream()
						.sorted((o1, o2) -> o1.getDescription().compareToIgnoreCase(o2.getDescription())).collect(Collectors.toList());
				break;
			}
		}
		int totalPage = (int) Math.ceil((double) listCourse / size);
		pageableCourseDto.setPage(new PageDto(totalPage, listCourse, pageCourse.size(), size));
		pageableCourseDto.setCourseImage(listImage);
		return pageableCourseDto;
	}

	public CourseImageDto findByIdCourse(String id) {
		CourseImageDto courseImageDto = new CourseImageDto();
		Course course = courseRepository.findById(id).get();
		courseImageDto.setDescription(course.getDescription());
		courseImageDto.setId(course.getIdDlFileEntry());
		courseImageDto.setName(course.getName());
		String fileName = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/dlFileEntry/viewImage/")
				.path(course.getIdDlFileEntry()).toUriString();
		courseImageDto.setLink(fileName);
		List<LessonCourseProjection> listLesson = courseRepository.findLessonByIdCourse(course.getId());
		String lesson = "";
		if (listLesson.size() > 0) {
			for (int i = 0; i < listLesson.size(); i++) {
				if (i < listLesson.size() - 1) {
					lesson += listLesson.get(i).getName() + ",";
				} else {
					lesson += listLesson.get(i).getName();
				}
			}
		} else {
			lesson = "Không có bài học nào trong khóa học này";
		}
		courseImageDto.setLesson(lesson);
		return courseImageDto;
	}
//	public Page<Course> searchAndPageableCourse(Integer page,Integer size,String input){
//		return courseRepository.findAllCourse(input, pageBasic.page(page, size, "DESC"));
//	}

	public Course findById(String id) {
		Course course = courseRepository.findById(id).get();
		return course;
	}

	public String deleteCourseByListId(List<String> id) {
		List<Course> course = courseRepository.findListCourse(id);
		courseRepository.deleteAll(course);
		return "Bạn đã xóa khóa học thành công";
	}

}
