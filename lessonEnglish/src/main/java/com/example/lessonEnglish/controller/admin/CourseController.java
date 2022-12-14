package com.example.lessonEnglish.controller.admin;
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
import com.example.lessonEnglish.dto.PageableCourseDto;
import com.example.lessonEnglish.dto.response.LessonImageResponse;
import com.example.lessonEnglish.entity.Course;
import com.example.lessonEnglish.service.CourseService;

@RestController
@RequestMapping("/api/v1/course")
@CrossOrigin(origins =  "*")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	
	
	@PostMapping("/insertCourse")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public String insertCourse(@RequestBody CourseDto courseDto) {
		return courseService.insertCourse(courseDto);
	}
	
	@PutMapping("/updateCourse/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public String updateCourse(@RequestBody CourseDto courseDto, @PathVariable("id") String id) {
		return courseService.updateCourse(courseDto, id);
	}
	
	@GetMapping("/findAll")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public PageableCourseDto findAll(@RequestParam(name="page",required = false, defaultValue = "1") Integer page,
			@RequestParam(name="size",required = false, defaultValue = "2") Integer size,
			@RequestParam(name="input",required = false,defaultValue = "") String input,
			@RequestParam(name="sort",required = false) String sort){
		return courseService.findAll(page,size,input,sort);
	}

	@GetMapping("/findAllCourse")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public List<Course> findAllCourse(){
		return courseService.findAllCourse();
	}
	
	@GetMapping("/findById")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public CourseImageDto findById(@RequestParam("id") String id) {
		return courseService.findByIdCourse(id);
	}
	
	@DeleteMapping("/delete")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteCourseById(@RequestBody List<String> id) {
		return courseService.deleteCourseByListId(id);
	}
	
	@GetMapping("/findLessonByIdCourse")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public List<LessonImageResponse> findAllLessonByIdCourse(@RequestParam("id") String id){
		return courseService.findAllLessonByIdCourse(id);
	}
	
	
}
