package com.example.lessonEnglish.service;

import java.text.SimpleDateFormat;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.lessonEnglish.dto.CustomerDto;
import com.example.lessonEnglish.dto.request.RequestDto;
import com.example.lessonEnglish.entity.Customer;
import com.example.lessonEnglish.entity.Logo;
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
	
	public String insertCustomer(CustomerDto customerDto) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			Customer customer = new Customer();
			customer.setAddress(customerDto.getAddress());
			customer.setGender(customerDto.getGender());
			String fileName = "";
			if (customerDto.getGender().equals("MALE")) {
				fileName = "avatar-nam.jpg";
			} else if (customerDto.getGender().equals("FEMALE")) {
				fileName = "avatar-nu.jpg";
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
	
	public String signin(RequestDto request, HttpServletRequest httpRequest) {
		try {
			Customer customer =customerRepository.findByEmail(request.getEmail()).get();
			if(customer !=null) {
				if(encoder.matches(request.getPassword(), customer.getPassword())) {
					String encoding = Base64.getEncoder().encodeToString((request.getEmail() + ":" + request.getPassword()).getBytes());
					HttpSession session = httpRequest.getSession();
					session.setAttribute("encoding", encoding);
					return encoding;
				}else {
					return "Tài khoản hoặc mật khẩu không đúng";
				}				
			}else {
				return "Tài khoản hoặc mật khẩu không đúng";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Đăng nhập thất bại";
			// TODO: handle exception
		}
	}
}
