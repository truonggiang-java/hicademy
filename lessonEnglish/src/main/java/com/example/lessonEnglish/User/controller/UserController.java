package com.example.lessonEnglish.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.dto.response.TokenResponse;
import com.example.lessonEnglish.jwt.JwtUtlis;

@RestController
@RequestMapping("/api/v2/user")
public class UserController {
	
	@Autowired
	private JwtUtlis jwtUtils;
	
	@GetMapping("/token")
	public TokenResponse getToken(@RequestParam("username") String username) {
		String token=jwtUtils.generateToken(username);
		return new TokenResponse(token);
	}
	
	@GetMapping("/getUserByToken")
	public String getUserByToken(@RequestParam("token") String token) {
		return jwtUtils.getUsernameByToken(token);
	}
}
