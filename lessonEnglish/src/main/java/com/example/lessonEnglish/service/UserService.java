package com.example.lessonEnglish.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.lessonEnglish.constants.Gender;
import com.example.lessonEnglish.dto.UserDto;
import com.example.lessonEnglish.dto.UserImageDto;
import com.example.lessonEnglish.entity.Logo;
import com.example.lessonEnglish.entity.Users;
import com.example.lessonEnglish.repository.LogoRepository;
import com.example.lessonEnglish.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LogoRepository logoRepository;

	@Autowired
	private PasswordEncoder encoder;

	public String insertUser(UserDto userDto) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			Users users = new Users();
			users.setAddress(userDto.getAddress());
			users.setGender(userDto.getGender());
			String fileName = "";
			if (userDto.getGender().equals("MALE")) {
				fileName = "avatar-nam.jpg";
			} else if (userDto.getGender().equals("FEMALE")) {
				fileName = "avatar-nu.jpg";
			}
			Logo logo = logoRepository.findByNameLogo(fileName);
			users.setDateOfBirth(format.parse(userDto.getDateOfBirth()));
			users.setEmail(userDto.getEmail());
			users.setName(userDto.getName());
			users.setIdLogo(logo.getId());
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

	public List<UserImageDto> findAllUser() {
		List<Users> listUsers = userRepository.findAllUser();
		List<UserImageDto> listUserImageDto = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		for (Users users : listUsers) {
			UserImageDto userImageDto = new UserImageDto();
			userImageDto.setEmail(users.getEmail());
			userImageDto.setGender(users.getGender());
			userImageDto.setId(users.getId());
			userImageDto.setName(users.getName());
			userImageDto.setRole(users.getRole());
			userImageDto.setDate(format.format(users.getDateOfBirth()));
			userImageDto.setPhone(users.getTelephone());
			userImageDto.setAddress(users.getAddress());
			String fileName = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/logo/view/")
					.path(users.getIdLogo()).toUriString();
			userImageDto.setLink(fileName);
			listUserImageDto.add(userImageDto);
		}
		return listUserImageDto;
	}

	public UserImageDto findByIdUser(String id) {
		Users users = userRepository.findByIdUser(id);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		UserImageDto userImageDto = new UserImageDto();
		userImageDto.setEmail(users.getEmail());
		userImageDto.setGender(users.getGender());
		userImageDto.setId(users.getId());
		userImageDto.setName(users.getName());
		userImageDto.setRole(users.getRole());
		userImageDto.setAddress(users.getAddress());
		userImageDto.setDate(format.format(users.getDateOfBirth()));
		userImageDto.setPhone(users.getTelephone());
		String fileName = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/logo/view/")
				.path(users.getIdLogo()).toUriString();
		userImageDto.setLink(fileName);

		return userImageDto;
	}
}
