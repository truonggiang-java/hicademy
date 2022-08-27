package com.example.lessonEnglish.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.dto.UserDto;
import com.example.lessonEnglish.dto.UserImageDto;
import com.example.lessonEnglish.dto.UserRoleDto;
import com.example.lessonEnglish.dto.request.RequestDto;
import com.example.lessonEnglish.dto.response.ResponseDto;
import com.example.lessonEnglish.jwt.JwtUtlis;
import com.example.lessonEnglish.security.service.UserDetailService;
import com.example.lessonEnglish.security.service.UserServiceImpl;
import com.example.lessonEnglish.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(value = "*")
public class UserController {
	
	@Autowired
	private JwtUtlis jwtUtils;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@PostMapping("/signin")
	public ResponseDto signin(@RequestBody RequestDto request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		} catch (Exception e) {
			throw new Exception("Bạn không có quyền");
		}
		UserServiceImpl userServiceImpl=(UserServiceImpl) userDetailService.loadUserByUsername(request.getEmail());
		String token=jwtUtils.generateToken(userServiceImpl);
		Date expriation=jwtUtils.getExpriationDateFromToken(token);
//		long expriationTime=expriation.getTime();
		return new ResponseDto(token,expriation);
		
	}
	
	@GetMapping("/getUserByToken")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public String getUserByToken(@RequestParam("token") String token) {
		return jwtUtils.getUsernameByToken(token);
	}
	@GetMapping("/getRoleByToken")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public UserRoleDto getRoleByToken(@RequestParam("token") String token) {
		return userService.getRoleByToken(token);
	}
	@PostMapping("/insertUser")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String insertUser(@RequestBody UserDto userDto) {
		return userService.insertUser(userDto);
	}
	
	@GetMapping("/findAll")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<UserImageDto> findAllUser() {
		return userService.findAllUser();
	}
	
	
	
	@GetMapping("/findById")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public UserImageDto findByIdUser(@RequestParam("id") String id) {
		return userService.findByIdUser(id);
	}
}
