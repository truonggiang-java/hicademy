package com.example.lessonEnglish.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.dto.TagsImageDto;
import com.example.lessonEnglish.service.TagsService;

@RestController
@RequestMapping("/api/v2/tags")
@CrossOrigin(origins =  "*")
public class TagsUserController {
	@Autowired
	private TagsService tagsService;
	
	@GetMapping("/randomTags")
	public List<TagsImageDto> randomTags(@RequestParam(name="number",required = false, defaultValue = "1") Integer number){
		return tagsService.randomTags(number);
	}
}
