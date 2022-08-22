package com.example.lessonEnglish.User.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.lessonEnglish.service.LogoService;

@RestController
@RequestMapping("/api/v2/logo")
@CrossOrigin(origins = "*")
public class LogoController {
	@Autowired
	private LogoService logoService;
	
	@PostMapping("/upload")
	public String updateFileLogo(@RequestParam("file") MultipartFile file) {
		return logoService.uploadToProject(file);
	}
	
	@GetMapping("/view")
	public ResponseEntity<byte[]> viewImage(@RequestParam("id") String id) throws IOException{
		return logoService.viewImage(id);
	}
}
