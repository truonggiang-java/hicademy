package com.example.lessonEnglish.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.lessonEnglish.entity.Users;
import com.example.lessonEnglish.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users users=userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy email này" + email));
		UserServiceImpl userService=new UserServiceImpl();
		List<GrantedAuthority> authority=new ArrayList<>();
		authority.add(new SimpleGrantedAuthority(users.getRole()));
		userService.setAuthorities(authority);
		userService.setEmail(users.getEmail());
		userService.setId(users.getId());
		userService.setPassword(users.getPassword());
		return userService;
	}
	
}
