package com.example.lessonEnglish.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.lessonEnglish.dto.ChangePasswordDto;
import com.example.lessonEnglish.dto.CustomerDto;
import com.example.lessonEnglish.dto.LogoDto;
import com.example.lessonEnglish.dto.UserImageDto;
import com.example.lessonEnglish.dto.request.RequestDto;
import com.example.lessonEnglish.dto.request.UserRequestDto;
import com.example.lessonEnglish.entity.Customer;
import com.example.lessonEnglish.service.CustomerService;
import com.example.lessonEnglish.service.LogoService;

@RestController
@RequestMapping("/api/v2/customer")
@CrossOrigin(value = "*")
public class CustomerControllerUser {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LogoService logoService;
	
	@PostMapping("/upload")
	public LogoDto updateFileLogo(@RequestParam("file") MultipartFile file) {
		return logoService.uploadToProject(file);
	}
	
	@PostMapping("/insert")
	public String insertUser(@RequestBody CustomerDto customerDto) {
		return customerService.insertCustomer(customerDto);
	}
	
	@PostMapping("/signin")
	public String signin(@RequestBody RequestDto request,HttpServletRequest httpRequest) throws Exception {
		return customerService.signin(request,httpRequest);
	}
	
	@GetMapping("/findById/{id}")
	public Customer findByIdCustomer(@PathVariable String id) {
		return customerService.findbyIdCustomer(id);
	}
	
	@PostMapping("/changePassword")
	public String changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
		return customerService.changePassword(changePasswordDto);
	}
	
	@PutMapping("/updateCustomer/{id}")
	public String updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable("id") String id) {
		return customerService.updateCustomer(userRequestDto, id);
	}
	
	@GetMapping("/findById")

	public UserImageDto findByIdUser(@RequestParam("id") String id) {
		return customerService.findByIdUser(id);
	}
}
