package com.example.lessonEnglish.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.lessonEnglish.entity.Customer;
import com.example.lessonEnglish.repository.CustomerRepository;

@Component
public class CustomerServiceImpl implements UserDetailsService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Không tồn tại tài khoản này: " + email));
		return new CustomerDetailService(customer);
	}

}
