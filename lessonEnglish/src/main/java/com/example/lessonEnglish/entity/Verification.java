package com.example.lessonEnglish.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="verification")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
		this.otp = RandomStringUtils.randomNumeric(4);
		this.exprieDate = LocalDateTime.now().plusMinutes(3);
		this.email = email;
	}
}
