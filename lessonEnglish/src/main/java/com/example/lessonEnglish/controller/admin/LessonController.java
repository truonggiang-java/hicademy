package com.example.lessonEnglish.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
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
import com.example.lessonEnglish.dto.PageableLessonDto;
import com.example.lessonEnglish.controller.user.BaseController;
import com.example.lessonEnglish.dto.LessonDto;
import com.example.lessonEnglish.dto.LessonImageDto;
import com.example.lessonEnglish.entity.Lesson;
import com.example.lessonEnglish.service.LessonService;

@RestController
@RequestMapping("/api/v1/lesson")
@CrossOrigin(origins = "*")
public class LessonController extends BaseController{
	@Autowired
	private LessonService lessonService;
	
	@PostMapping("/insertLesson")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<?> insertLesson(@RequestBody @Valid LessonDto lessonDto,Errors er) {
		if(er.hasErrors()) {
			return errorsValidation(er);
		}else {
			return validation(lessonService.insertLesson(lessonDto));
		}
	}
	
	@GetMapping("/findAll")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public List<Lesson> findAllLesson(){
		return lessonService.findAllLesson();
	}

	@GetMapping("/findAllPageable")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public PageableLessonDto findAll(@RequestParam(name="page",required = false, defaultValue = "1") Integer page,
			@RequestParam(name="size",required = false, defaultValue = "8") Integer size,
			@RequestParam(name="input",required = false,defaultValue = "") String input){
		return lessonService.findAll(page,size,input);
	}

	@DeleteMapping("/delete")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteLessonById(@RequestBody List<String> id) {
		return lessonService.deleteLessonByListId(id);
	}

	@PutMapping("/updateLesson/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<?> updateLesson(@RequestBody @Valid LessonDto lessonDto, @PathVariable("id") String id,Errors error) {
		if(error.hasErrors()) {
			return errorsValidation(error);
		}else {
			return validation(lessonService.updateLesson(lessonDto, id));
		}
	}

	@GetMapping("/findById")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public LessonImageDto findById(@RequestParam("id") String id) {
		return lessonService.findByIdLesson(id);
	}
}
