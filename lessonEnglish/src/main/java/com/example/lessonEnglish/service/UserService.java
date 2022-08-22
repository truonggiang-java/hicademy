package com.example.lessonEnglish.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.lessonEnglish.constants.Gender;
import com.example.lessonEnglish.dto.UserDto;
import com.example.lessonEnglish.entity.Users;
import com.example.lessonEnglish.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public String insertUser(UserDto userDto) {
		try {
			SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
			Users users=new Users();
			users.setAddress(userDto.getAddress());
			users.setGender(userDto.getGender());
			users.setDateOfBirth(format.parse(userDto.getDateOfBirth()));
			users.setEmail(userDto.getEmail());
			users.setIdLogo(userDto.getIdLogo());
			users.setName(userDto.getName());
			users.setRole(userDto.getRole().toUpperCase());
			users.setPassword(encoder.encode(userDto.getPassword()));
			users.setTelephone(userDto.getTelephone());
			userRepository.save(users);
			return "Bạn thêm người dùng thành công";
		} catch (Exception e) {
			return "Bạn thêm người dùng thất bại";
			// TODO: handle exception
		}
	}
}
