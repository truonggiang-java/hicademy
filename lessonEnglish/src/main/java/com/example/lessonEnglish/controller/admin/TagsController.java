package com.example.lessonEnglish.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.dto.TagsDto;
import com.example.lessonEnglish.dto.TagsImageDto;
import com.example.lessonEnglish.service.TagsService;

@RestController
@RequestMapping("/api/v1/tags")
@CrossOrigin(origins = "*")
public class TagsController {
	@Autowired
	private TagsService tagsService;
	
	@PostMapping("/insertTags")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public String inserTags(@RequestBody TagsDto tagsDto) {
		return tagsService.insertTags(tagsDto);
	}
	
	@GetMapping("/findAll")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public List<TagsImageDto> findAllTags() {
		return tagsService.listTagsImageDto();	
	}
}
