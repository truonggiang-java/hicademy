package com.example.lessonEnglish.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.lessonEnglish.dto.ChangePasswordDto;
import com.example.lessonEnglish.dto.InformationUserResetPassword;
import com.example.lessonEnglish.dto.UserDto;
import com.example.lessonEnglish.dto.UserImageDto;
import com.example.lessonEnglish.dto.UserRoleDto;
import com.example.lessonEnglish.dto.request.UserRequestDto;
import com.example.lessonEnglish.entity.Logo;
import com.example.lessonEnglish.entity.Users;
import com.example.lessonEnglish.jwt.JwtUtlis;
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

	@Autowired
	private JwtUtlis jwtUtils;

	@Autowired
	private EmailService emailService;

	public String insertUser(UserDto userDto) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			Users users = new Users();
			users.setAddress(userDto.getAddress());
			users.setGender(userDto.getGender());
			String fileName = "";
			if (userDto.getGender().equals("MALE")) {
				fileName = "ava_nam.png";
			} else if (userDto.getGender().equals("FEMALE")) {
				fileName = "ava_nu.png";
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
			e.printStackTrace();
			return "Bạn thêm người dùng thất bại";
			// TODO: handle exception
		}
	}

	public String updateUser(UserRequestDto userRequestDto, String id) {
		try {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Users users = userRepository.findById(id).get();
			users.setAddress(userRequestDto.getAddress());
			if (userRequestDto.getRole() != null) {
				users.setRole(userRequestDto.getRole());
			}
			if (userRequestDto.getIdLogo() != null) {
				users.setIdLogo(userRequestDto.getIdLogo());
			}
			users.setName(userRequestDto.getName());
			users.setGender(userRequestDto.getGender());
			users.setTelephone(userRequestDto.getPhoneNumber());
			users.setDateOfBirth(format.parse(userRequestDto.getDate()));
			userRepository.save(users);
			return "Cập nhật người dùng thành công";
		} catch (Exception e) {
			e.printStackTrace();
			return "Cập nhật người dùng thất bại";
			// TODO: handle exception
		}
	}

	public List<UserImageDto> findAllUser(String input) {
		List<Users> listUsers = userRepository.findAllUser(input);
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
		userImageDto.setIdLogo(users.getIdLogo());
		String fileName = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/logo/view/")
				.path(users.getIdLogo()).toUriString();
		userImageDto.setLink(fileName);

		return userImageDto;
	}

	public UserRoleDto getRoleByToken(String token) {
		String email = jwtUtils.getUsernameByToken(token);
		UserRoleDto userRoleDto = new UserRoleDto();
		Users user = userRepository.findByEmail(email).get();
		userRoleDto.setName(user.getName());
		userRoleDto.setRole(user.getRole());
		userRoleDto.setId(user.getId());
		String fileName = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/logo/view/")
				.path(user.getIdLogo()).toUriString();
		userRoleDto.setLinkUrl(fileName);
		return userRoleDto;
	}

	public String resetPassword(String email) {
		try {
			String random = RandomStringUtils.randomAlphabetic(6);
			String body = "<div  style='background-color: #008CBA;\n" + "	border: none;\n" + "  color: white;\n"
					+ "  padding: 20px 100px;\n" + "  text-align: center;\n" + "  text-decoration: none;\n"
					+ "  display: inline-block;\n" + "  font-size: 16px;\n" + "  margin: 4px 2px;\n" + "  '>" + random
					+ "</div>";
			Users users = userRepository.findByEmail(email).get();
			users.setPassword(encoder.encode(random));
			userRepository.save(users);
			emailService.sendEmailAttachment(email, body, "Password current");
			return "Reset password success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Reset password fail";
		}
	}

	public String informationUserResetPassword(InformationUserResetPassword informationUserResetPassword) {
		try {
			String random = RandomStringUtils.randomAlphabetic(6);
			String body = "<div  style='background-color: #008CBA;\n" + "	border: none;\n" + "  color: white;\n"
					+ "  padding: 20px 100px;\n" + "  text-align: center;\n" + "  text-decoration: none;\n"
					+ "  display: inline-block;\n" + "  font-size: 16px;\n" + "  margin: 4px 2px;\n" + "  '>" + random
					+ "</div>";
			Users users = userRepository.findByEmail(informationUserResetPassword.getEmail()).get();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (users != null) {
				String usersDate=format.format(users.getDateOfBirth());
				if (users.getEmail().equals(informationUserResetPassword.getEmail())
						&& users.getTelephone().equals(informationUserResetPassword.getTelephone())
						&& users.getName().equals(informationUserResetPassword.getName())
						&& usersDate.equals(informationUserResetPassword.getDateBirthDay())) {
					
					users.setPassword(encoder.encode(random));
					userRepository.save(users);
					emailService.sendEmailAttachment(informationUserResetPassword.getEmail(), body, "Password current");
					return "Reset password success";
				}else {
					return "Reset password fail";
				}
			} else {
				return "Reset password fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Reset password fail";
		}
	}

	public String changePassword(ChangePasswordDto changePasswordDto) {
		try {
			Users users = userRepository.findByEmail(changePasswordDto.getEmail()).get();
			if (encoder.matches(changePasswordDto.getCurrentPassword(), users.getPassword())) {
				users.setPassword(encoder.encode(changePasswordDto.getChangePassword()));
				userRepository.save(users);
				return "Change password success";
			} else {
				return "Incorrect password";
			}
		} catch (Exception e) {
			return "Change password fail";
			// TODO: handle exception
		}
	}

	public String deleteUserByListId(List<String> id) {
		List<Users> lesson = userRepository.findListIdUser(id);
		userRepository.deleteAll(lesson);
		return "Bạn đã xóa người dùng thành công";
	}
}
