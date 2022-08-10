package com.example.lessonEnglish.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.lessonEnglish.dto.PageableLessonDto;
import com.example.lessonEnglish.dto.LessonDto;
import com.example.lessonEnglish.entity.Lesson;
import com.example.lessonEnglish.service.LessonService;

@RestController
@RequestMapping("/api/v1/lesson")
@CrossOrigin(origins = "*")
public class LessonController {
	@Autowired
	private LessonService lessonService;
	
	@PostMapping("/insertLesson")
	private String insertLesson(@RequestBody LessonDto lessonDto) {
		return lessonService.insertLesson(lessonDto);
	}
	
	@GetMapping("/findAll")
	private List<Lesson> findAllLesson(){
		return lessonService.findAllLesson();
	}

	@GetMapping("/findAllPageable")
	public PageableLessonDto findAll(@RequestParam(name="page",required = false, defaultValue = "1") Integer page,
			@RequestParam(name="size",required = false, defaultValue = "8") Integer size,
			@RequestParam(name="input",required = false,defaultValue = " ") String input){
		return lessonService.findAll(page,size,input);
	}
}
