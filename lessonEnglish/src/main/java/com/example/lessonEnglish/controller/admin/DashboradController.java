package com.example.lessonEnglish.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.dto.DashboradDto;
import com.example.lessonEnglish.service.DashboradService;

@RestController
@RequestMapping("/api/v1/dashborad")
@CrossOrigin(origins =  "*")
public class DashboradController {
	
	@Autowired
	private DashboradService dashboradService;
	@GetMapping("/list")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public List<DashboradDto> dashborad(){
		return dashboradService.dashborad();
	}
	
}
