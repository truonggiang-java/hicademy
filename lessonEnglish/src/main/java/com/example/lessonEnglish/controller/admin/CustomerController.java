package com.example.lessonEnglish.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.example.lessonEnglish.dto.CustomerDto;
import com.example.lessonEnglish.dto.UserImageDto;
import com.example.lessonEnglish.dto.request.UserRequestDto;
import com.example.lessonEnglish.entity.Customer;
import com.example.lessonEnglish.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin(value = "*")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/insertCustomer")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String insertCustomer(@RequestBody CustomerDto customerDto) {
		return customerService.insertCustomer(customerDto);
	}
	
	@GetMapping("/findById/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public UserImageDto findByIdCustomer(@PathVariable String id) {
		return customerService.findByIdUser(id);
	}
	
	@GetMapping("/findAll")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<UserImageDto> findAllUser(@RequestParam(name="input",required = false,defaultValue = "") String input) {
		return customerService.findAllUser(input);
	}
	
	@PutMapping("/updateUser/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable("id") String id) {
		return customerService.updateCustomer(userRequestDto, id);
	}
	@GetMapping("/resetPassword/{email}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String resetPassword(@PathVariable("email") String email) {
		return customerService.resetPassword(email);
	}
	
	@DeleteMapping("/delete")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteUserById(@RequestBody List<String> id) {
		return customerService.deleteUserByListId(id);
	}
	
}
