package com.example.lessonEnglish.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name="verification")
@Data
public class Verification {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="id")
	private String id;
	
	@Column(name="otp")
	private String otp;
	
	
	@Column(name="exprie_date")
	private LocalDateTime exprieDate;
	
	@Column(name="email")
	private String email;

	public Verification(String email) {
		super();
		this.otp = RandomStringUtils.randomAlphabetic(4);
		this.exprieDate = LocalDateTime.now().plusMinutes(1);
		this.email = email;
	}

	
	
	
}
