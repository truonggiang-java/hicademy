package com.example.lessonEnglish.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.dto.UserDto;
import com.example.lessonEnglish.dto.response.TokenResponse;
import com.example.lessonEnglish.jwt.JwtUtlis;
import com.example.lessonEnglish.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	private JwtUtlis jwtUtils;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/token")
	public TokenResponse getToken(@RequestParam("username") String username) {
		String token=jwtUtils.generateToken(username);
		return new TokenResponse(token);
	}
	
	@GetMapping("/getUserByToken")
	public String getUserByToken(@RequestParam("token") String token) {
		return jwtUtils.getUsernameByToken(token);
	}
	
	@PostMapping("/insertUser")
	public String insertUser(@RequestBody UserDto userDto) {
		return userService.insertUser(userDto);
	}
}
