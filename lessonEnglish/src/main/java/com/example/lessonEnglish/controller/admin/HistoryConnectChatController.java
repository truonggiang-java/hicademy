package com.example.lessonEnglish.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.service.HistoryConnectChatService;

@RestController
public class HistoryConnectChatController {
	
	@Autowired
	private HistoryConnectChatService historyConnectChatService;
	
	@GetMapping("/ipAddress")
	public String getIpAddress(HttpServletRequest request) {
		return historyConnectChatService.getClientIp(request);
	}
}
