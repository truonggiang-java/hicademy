package com.example.lessonEnglish.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.lessonEnglish.constants.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseEntity{
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private Gender gender;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@Column(name="address")
	private String address;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="id_logo")
	private String idLogo;
	
	@Column(name="role")
	private String role;
	
	@Column(name="old_password")
	private String oldPassword;
	
	@Column(name="old_password_date")
	private Timestamp oldPasswordDate;
}
