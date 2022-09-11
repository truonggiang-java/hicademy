package com.example.lessonEnglish.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.lessonEnglish.dto.ChangePasswordDto;
import com.example.lessonEnglish.dto.CustomerDto;
import com.example.lessonEnglish.dto.InformationUserResetPassword;
import com.example.lessonEnglish.dto.SigninCustomerDto;
import com.example.lessonEnglish.dto.UserImageDto;
import com.example.lessonEnglish.dto.request.RequestDto;
import com.example.lessonEnglish.dto.request.UserRequestDto;
import com.example.lessonEnglish.entity.Customer;
import com.example.lessonEnglish.entity.Logo;
import com.example.lessonEnglish.entity.Users;
import com.example.lessonEnglish.error.CustomError;
import com.example.lessonEnglish.repository.CustomerRepository;
import com.example.lessonEnglish.repository.LogoRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private LogoRepository logoRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private EmailService emailService;
	
	public String insertCustomer(CustomerDto customerDto) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			Customer customer = new Customer();
			customer.setAddress(customerDto.getAddress());
			customer.setGender(customerDto.getGender());
			String fileName = "";
			if (customerDto.getGender().equals("MALE")) {
				fileName = "ava_nam.png";
			} else if (customerDto.getGender().equals("FEMALE")) {
				fileName = "ava_nu.png";
			}
			Logo logo = logoRepository.findByNameLogo(fileName);
			customer.setDateOfBirth(format.parse(customerDto.getDateOfBirth()));
			customer.setEmail(customerDto.getEmail());
			customer.setName(customerDto.getName());
			customer.setIdLogo(logo.getId());
			customer.setRole("NORMAL");
			customer.setPassword(encoder.encode(customerDto.getPassword()));
			customer.setTelephone(customerDto.getTelephone());
			customerRepository.save(customer);
			return "Bạn thêm người dùng thành công";
		} catch (Exception e) {
			e.printStackTrace();
			return "Bạn thêm người dùng thất bại";
			// TODO: handle exception
		}
	}
	public String updateCustomer(UserRequestDto userRequestDto, String id) {
		try {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Customer users = customerRepository.findById(id).get();
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
			customerRepository.save(users);
			return "Cập nhật người dùng thành công";
		} catch (Exception e) {
			e.printStackTrace();
			return "Cập nhật người dùng thất bại";
			// TODO: handle exception
		}
	}
	public Customer findbyIdCustomer(String id) {
		return customerRepository.findById(id).get();
	}
	
	public String resetPassword(String email) {
		try {
			String random = RandomStringUtils.randomAlphabetic(6);
			String body = "<div  style='background-color: #008CBA;\n" + "	border: none;\n" + "  color: white;\n"
					+ "  padding: 20px 100px;\n" + "  text-align: center;\n" + "  text-decoration: none;\n"
					+ "  display: inline-block;\n" + "  font-size: 16px;\n" + "  margin: 4px 2px;\n" + "  '>" + random
					+ "</div>";
			Customer users = customerRepository.findByEmail(email).get();
			users.setPassword(encoder.encode(random));
			customerRepository.save(users);
			emailService.sendEmailAttachment(email, body, "Password current");
			return "Reset password success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Reset password fail";
		}
	}
	
	public UserImageDto findByIdUser(String id) {
		Customer users = customerRepository.findByIdUser(id);

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
	
	public String changePassword(ChangePasswordDto changePasswordDto) {
		try {
			Customer users = customerRepository.findByEmail(changePasswordDto.getEmail()).get();
			if (encoder.matches(changePasswordDto.getCurrentPassword(), users.getPassword())) {
				users.setPassword(encoder.encode(changePasswordDto.getChangePassword()));
				customerRepository.save(users);
				return "Change password success";
			} else {
				return "Incorrect password";
			}
		} catch (Exception e) {
			return "Change password fail";
			// TODO: handle exception
		}
	}
	public String informationCustomerResetPassword(InformationUserResetPassword informationUserResetPassword) {
		try {
			String random = RandomStringUtils.randomAlphabetic(6);
			String body = "<div  style='background-color: #008CBA;\n" + "	border: none;\n" + "  color: white;\n"
					+ "  padding: 20px 100px;\n" + "  text-align: center;\n" + "  text-decoration: none;\n"
					+ "  display: inline-block;\n" + "  font-size: 16px;\n" + "  margin: 4px 2px;\n" + "  '>" + random
					+ "</div>";
			Customer users = customerRepository.findByEmail(informationUserResetPassword.getEmail()).get();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (users != null) {
				String usersDate=format.format(users.getDateOfBirth());
				if (users.getEmail().equals(informationUserResetPassword.getEmail())
						&& users.getTelephone().equals(informationUserResetPassword.getTelephone())
						&& users.getName().equals(informationUserResetPassword.getName())
						&& usersDate.equals(informationUserResetPassword.getDateBirthDay())) {
					
					users.setPassword(encoder.encode(random));
					customerRepository.save(users);
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
	
	public List<UserImageDto> findAllUser(String input) {
		List<Customer> listUsers = customerRepository.findAllCustomer(input);
		List<UserImageDto> listUserImageDto = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		for (Customer users : listUsers) {
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
	
	public SigninCustomerDto signin(RequestDto request, HttpServletRequest httpRequest) throws CustomError {
		try {
			SigninCustomerDto signinCustomerDto=new SigninCustomerDto();
			Customer customer =customerRepository.findByEmail(request.getEmail()).get();
			if(customer !=null) {
				if(encoder.matches(request.getPassword(), customer.getPassword())) {
					String encoding = Base64.getEncoder().encodeToString((request.getEmail() + ":" + request.getPassword()).getBytes());
					signinCustomerDto.setId(customer.getId());
					signinCustomerDto.setToken(encoding);
					return signinCustomerDto;
				}else {
					throw new CustomError("Sai tài khoản hoặc mật khẩu");
				}				
			}else {
				throw new CustomError("Email không tồn tại");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomError("Sai tài khoản hoặc mật khẩu");
		}
	}
	
	public String deleteUserByListId(List<String> id) {
		List<Customer> lesson = customerRepository.findListIdUser(id);
		customerRepository.deleteAll(lesson);
		return "Bạn đã xóa người dùng thành công";
	}
}
