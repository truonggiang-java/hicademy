package com.example.lessonEnglish.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.example.lessonEnglish.dto.MiniLessonDto;
import com.example.lessonEnglish.service.MiniLessonService;

@RestController
@RequestMapping("/api/v2/miniLesson")
@CrossOrigin(origins = "*")
public class MiniLessonCustomerController {
	@Autowired
	private MiniLessonService miniLessonService;
	
	@PostMapping("/insertMiniLesson")
	public ResponseEntity<?> insertLesson(@RequestBody MiniLessonDto miniLessonDto){
		return miniLessonService.insertMiniLesson(miniLessonDto);
	}
	
	@PutMapping("/updateMiniLesson/{id}")
	public ResponseEntity<?> updateLesson(@RequestBody MiniLessonDto miniLessonDto, @PathVariable String id){
		return miniLessonService.updateMiniLesson(miniLessonDto,id);
	}
	
	@DeleteMapping("/deleteMiniLesson")
	public ResponseEntity<?> deleteMiniLesson(@RequestBody List<String> id){
		return miniLessonService.deleteMiniLesson(id);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAllMiniLesson(@RequestParam(name="page",required = false, defaultValue = "1") Integer page,
			@RequestParam(name="size",required = false, defaultValue = "8") Integer size,
			@RequestParam(name="input",required = false,defaultValue = "") String input){
		return miniLessonService.findAllMiniLesson(page, size, input);
	}
	@GetMapping("/findByIdLesson/{id}")
	public ResponseEntity<?> findById(@PathVariable String id){
		return miniLessonService.findById(id);
	}
}
