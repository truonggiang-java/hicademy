package com.example.lessonEnglish.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.dto.FeedBackDto;
import com.example.lessonEnglish.service.FeedBackService;

@RestController
@RequestMapping("/api/v2/feedBack")
@CrossOrigin(origins =  "*")
public class FeedBackControllerUser {
	@Autowired
	private FeedBackService feedBackService;
	
	@PostMapping("/insertFeedBack")
	public String insert(@RequestBody FeedBackDto feedBackDto) {
		return feedBackService.insertFeedBack(feedBackDto);
	}
}	
