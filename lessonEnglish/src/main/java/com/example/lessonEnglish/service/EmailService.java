package com.example.lessonEnglish.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public String sendEmailAttachment(String toEmail, String body, String subject)
			throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setText(body,true);
		mimeMessageHelper.setSubject(subject);
		
		javaMailSender.send(mimeMessage);
		return "Gửi mail và tệp đính kèm thành công";
	}
}
