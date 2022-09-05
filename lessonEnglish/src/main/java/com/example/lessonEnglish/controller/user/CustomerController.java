package com.example.lessonEnglish.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.dto.CustomerDto;
import com.example.lessonEnglish.service.CustomerService;

@RestController
@RequestMapping("/api/v2/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/insert")
	public String insertUser(@RequestBody CustomerDto customerDto) {
		return customerService.insertCustomer(customerDto);
	}
}