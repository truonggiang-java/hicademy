package com.example.lessonEnglish.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.service.VerificationService;

@RestController
@RequestMapping("/api/v2/verification")
@CrossOrigin(value = "*")
public class VerificationController {
	@Autowired
	private VerificationService verificationSerivce;
	
	@GetMapping("/sendEmail")
	public String sendEmailGetOtp(@RequestParam("email") String email) {
		return verificationSerivce.sendEmailGetOtp(email);
	}
	
	@GetMapping("/verficationOtp")
	public String verficationOtp(@RequestParam("email") String email,@RequestParam("otp") String otp) {
		return verificationSerivce.verficationOtp(otp, email);
	}
}
