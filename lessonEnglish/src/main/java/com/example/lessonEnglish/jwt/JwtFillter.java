package com.example.lessonEnglish.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.lessonEnglish.security.service.UserDetailService;
import com.example.lessonEnglish.security.service.UserServiceImpl;

@Component
public class JwtFillter extends OncePerRequestFilter {

	@Autowired
	private JwtUtlis jwtUtils;

	@Autowired
	private UserDetailService userDetailServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorize = request.getHeader("Authorization");
		String token = null;
		String email = null;

		if (authorize != null && authorize.startsWith("Bearer ")) {
			token = authorize.substring(7);
			email = jwtUtils.getUsernameByToken(token);
		}

		if (email != null) {
			UserServiceImpl userDetailService = (UserServiceImpl) userDetailServiceImpl.loadUserByUsername(email);
			if (jwtUtils.validateToken(token, userDetailService)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetailService, null, userDetailService.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				System.out.println(SecurityContextHolder.getContext().getAuthentication());
			}
		}
		filterChain.doFilter(request, response);
	}

}
