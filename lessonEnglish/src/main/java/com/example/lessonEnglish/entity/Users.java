package com.example.lessonEnglish.entity;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import com.example.lessonEnglish.constants.Gender;
import com.example.lessonEnglish.validation.ValidationUserType;
import com.fasterxml.jackson.annotation.JsonFormat;

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
	@ValidationUserType
	private String gender;
	
	@Column(name="date_of_birth")
	@Past(message="Ngày sinh không được là ngày hiện tại")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	
	@Column(name="address")
	@NotBlank(message="Địa chỉ không được để trống")
	private String address;
	
	@Column(name="telephone")
	@NotBlank(message="Điện thoại không được để trống")
	private String telephone;
	
	
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
