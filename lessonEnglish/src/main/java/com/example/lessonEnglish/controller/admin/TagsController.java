package com.example.lessonEnglish.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.dto.PageableDlFileEntryDto;
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
	
	@GetMapping(value = "/findAllTags")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public PageableDlFileEntryDto findAllPage(@RequestParam(name="page",required = false, defaultValue = "1") Integer page,
			@RequestParam(name="size",required = false, defaultValue = "6") Integer size,
			@RequestParam(name="input",required = false,defaultValue = ".") String input) {
		return tagsService.findAll(page, size, input);
	}
	
	@DeleteMapping("/deleteTags")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteTags(@RequestBody List<String> id) {
		return tagsService.deleteTags(id);
	}
}
