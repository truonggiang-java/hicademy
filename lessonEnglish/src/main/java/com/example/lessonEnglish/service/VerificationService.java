package com.example.lessonEnglish.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lessonEnglish.entity.Verification;
import com.example.lessonEnglish.repository.VerificationRepository;

@Service
public class VerificationService {
	@Autowired
	private VerificationRepository verificationRepository;
	
	@Autowired
	private EmailService emailService;
	
	public String sendEmailGetOtp(String email) {
		try {
			Optional<Verification> getEmail=verificationRepository.findEmail(email);
			if(getEmail.isPresent()) {
				verificationRepository.deleteById(getEmail.get().getId());
			}
			Verification verification= new Verification(email);
			verificationRepository.save(verification);
			String body = "<div  style='background-color: #008CBA;\n" + "	border: none;\n" + "  color: white;\n"
					+ "  padding: 20px 100px;\n" + "  text-align: center;\n" + "  text-decoration: none;\n"
					+ "  display: inline-block;\n" + "  font-size: 16px;\n" + "  margin: 4px 2px;\n" + "  '>" + verification.getOtp()
					+ "</div>";
			emailService.sendEmailAttachment(email, body, "Registration code OTP");
			return "Đã gửi email thành công";
		} catch (Exception e) {
			e.printStackTrace();
			return "email không tồn tại";
			// TODO: handle exception
		}
	}
	
	public String verficationOtp(String otp,String email) {
		try {
			Verification verification=verificationRepository.findVerificationByOtpAndEmail(otp,email);
			LocalDateTime date=LocalDateTime.now();
			if(!date.isBefore(verification.getExprieDate())) {
				Optional<Verification> getEmail=verificationRepository.findEmail(email);
				if(getEmail.isPresent()) {
					verificationRepository.deleteById(getEmail.get().getId());
				}
				return "Mã otp thành công";
				
			}else {
				return "Mã otp hết hạn";
			}
		} catch (Exception e) {
			return "Lỗi mã otp";
			// TODO: handle exception
		}
	}
}
