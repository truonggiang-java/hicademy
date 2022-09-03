package com.example.lessonEnglish.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity{
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@Column(name="address")
	private String address;
	
	@Column(name="telephone")
	private String telephone;
	
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="id_logo")
	private String idLogo;
	
	@Column(name="role")
	private String role;
}
