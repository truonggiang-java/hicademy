package com.example.lessonEnglish.controller.admin;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.dto.ChangePasswordDto;
import com.example.lessonEnglish.dto.InformationUserResetPassword;
import com.example.lessonEnglish.dto.UserDto;
import com.example.lessonEnglish.dto.UserImageDto;
import com.example.lessonEnglish.dto.UserRoleDto;
import com.example.lessonEnglish.dto.request.RequestDto;
import com.example.lessonEnglish.dto.request.UserRequestDto;
import com.example.lessonEnglish.dto.response.ResponseDto;
import com.example.lessonEnglish.entity.HistorySigin;
import com.example.lessonEnglish.entity.Users;
import com.example.lessonEnglish.jwt.JwtUtlis;
import com.example.lessonEnglish.repository.HistoryConnectChatRepository;
import com.example.lessonEnglish.security.service.UserDetailService;
import com.example.lessonEnglish.security.service.UserServiceImpl;
import com.example.lessonEnglish.service.HistoryConnectChatService;
import com.example.lessonEnglish.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(value = "*")
public class UserController {
	
	@Autowired
	private HistoryConnectChatRepository historyConChatRepository;
	
	@Autowired
	private HistoryConnectChatService historyConnectChatService;
	
	@Autowired
	private JwtUtlis jwtUtils;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@PostMapping("/signin")
	public ResponseDto signin(@RequestBody RequestDto request,HttpServletRequest requestDto) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		} catch (Exception e) {
			throw new Exception("Bạn không có quyền");
		}
		HistorySigin historySigin=new HistorySigin();
		historySigin.setDate(new Date());
		historySigin.setIp(historyConnectChatService.getClientIp(requestDto));
		
		historySigin.setName(request.getEmail());
		historySigin.setStatus("JOIN");
		historyConChatRepository.save(historySigin);
		UserServiceImpl userServiceImpl=(UserServiceImpl) userDetailService.loadUserByUsername(request.getEmail());
		String token=jwtUtils.generateToken(userServiceImpl);
		Date expriation=jwtUtils.getExpriationDateFromToken(token);
//		long expriationTime=expriation.getTime();
		return new ResponseDto(token,expriation);
		
	}
	@GetMapping("/list/async")
	public List<Users> getAllUsersAsync()
	{
		return userService.getAllUserAsync();
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
	public Users insertUser(@RequestBody @Valid UserDto userDto) throws ParseException {
		return userService.insertUser(userDto);
	}
	@PutMapping("/updateUser/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public String updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable("id") String id) {
		return userService.updateUser(userRequestDto, id);
	}
	@GetMapping("/findAll")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<UserImageDto> findAllUser(@RequestParam(name="input",required = false,defaultValue = "") String input) {
		return userService.findAllUser(input);
	}

	
	@GetMapping("/findById")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public UserImageDto findByIdUser(@RequestParam("id") String id) {
		return userService.findByIdUser(id);
	}
	
	@GetMapping("/resetPassword/{email}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String resetPassword(@PathVariable("email") String email) {
		return userService.resetPassword(email);
	}
	
	@PostMapping("/informationResetPassword")
	public String informationResetPassword(@RequestBody InformationUserResetPassword informationUserResetPassword) {
		return userService.informationUserResetPassword(informationUserResetPassword);
	}
	
	@PostMapping("/changePassword")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public String changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
		return userService.changePassword(changePasswordDto);
	}
	
	@DeleteMapping("/delete")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteUserById(@RequestBody List<String> id) {
		return userService.deleteUserByListId(id);
	}
}
