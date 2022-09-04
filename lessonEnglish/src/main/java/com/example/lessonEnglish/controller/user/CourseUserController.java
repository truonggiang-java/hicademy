package com.example.lessonEnglish.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.dto.CourseDto;
import com.example.lessonEnglish.dto.CourseImageDto;
import com.example.lessonEnglish.dto.CourseImageUserDto;
import com.example.lessonEnglish.dto.PageableCourseDto;
import com.example.lessonEnglish.dto.response.LessonImageResponse;
import com.example.lessonEnglish.entity.Course;
import com.example.lessonEnglish.service.CourseService;

@RestController
@RequestMapping("/api/v2/course")
@CrossOrigin(origins =  "*")
public class CourseUserController {
	@Autowired
	private CourseService courseService;

	@GetMapping("/findAllCourse")
	// @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public List<CourseImageUserDto> findAllCourse(){
		return courseService.findAllCouserUser();
	}
	
	@GetMapping("/findById")
	// @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public CourseImageDto findById(@RequestParam("id") String id) {
		return courseService.findByIdCourse(id);
	}
	
	@GetMapping("/findLessonByIdCourse")
	// @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public List<LessonImageResponse> findAllLessonByIdCourse(@RequestParam("id") String id){
		return courseService.findAllLessonByIdCourse(id);
	}
	
}

