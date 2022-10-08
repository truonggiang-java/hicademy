package com.example.lessonEnglish.controller.user;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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
import com.example.lessonEnglish.dto.InformationUserResetPassword;
import com.example.lessonEnglish.dto.LogoDto;
import com.example.lessonEnglish.dto.SigninCustomerDto;
import com.example.lessonEnglish.dto.UserImageDto;
import com.example.lessonEnglish.dto.request.RequestDto;
import com.example.lessonEnglish.dto.request.UserRequestDto;
import com.example.lessonEnglish.entity.Customer;
import com.example.lessonEnglish.error.CustomError;
import com.example.lessonEnglish.service.CustomerService;
import com.example.lessonEnglish.service.LogoService;

@RestController
@RequestMapping("/api/v2/customer")
@CrossOrigin(value = "*")
public class CustomerControllerUser extends BaseController{
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LogoService logoService;
	
	@PostMapping("/upload")
	public LogoDto updateFileLogo(@RequestParam("file") MultipartFile file) {
		return logoService.uploadToProject(file);
	}
	
	public ResponseEntity<?> insertCustomer(@RequestBody @Valid CustomerDto customerDto, Errors ex) {
		String errorCustomer = ex.getFieldErrors().stream()
				.map(field -> field.getField() + ": " + field.getDefaultMessage()).collect(Collectors.joining(", "));
		if (errorCustomer != null && !errorCustomer.isBlank() && !errorCustomer.isEmpty()) {
			return new ResponseEntity<>(errorCustomer, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(customerService.insertCustomer(customerDto),HttpStatus.OK);
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody RequestDto request,HttpServletRequest httpRequest) throws CustomError {
		try {
			SigninCustomerDto signinCustomerDto=customerService.signin(request,httpRequest);
			return response(ok(signinCustomerDto));
		} catch (Exception e) {
			e.printStackTrace();
			return errorHttp(error(e));
			// TODO: handle exception
		}
		
	}
	
	@GetMapping("/findById/{id}")
	public Customer findByIdCustomer(@PathVariable String id) {
		return customerService.findbyIdCustomer(id);
	}
	
	@PostMapping("/changePasswordCustomer")
	public String changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
		return customerService.changePassword(changePasswordDto);
	}
	
	@PutMapping("/updateCustomer/{id}")
	public String updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable("id") String id) {
		return customerService.updateCustomer(userRequestDto, id);
	}
	
	@PostMapping("/informationResetPassword")
	public ResponseEntity<?> informationResetPassword(@RequestBody InformationUserResetPassword informationUserResetPassword) {
		return customerService.informationCustomerResetPassword(informationUserResetPassword);
	}
	@GetMapping("/findById")
	public UserImageDto findByIdUser(@RequestParam("id") String id) {
		return customerService.findByIdUser(id);
	}
}
