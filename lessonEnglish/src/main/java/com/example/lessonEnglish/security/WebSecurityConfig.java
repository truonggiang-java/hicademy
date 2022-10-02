package com.example.lessonEnglish.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.lessonEnglish.jwt.JwtFillter;
import com.example.lessonEnglish.security.service.CustomerServiceImpl;
import com.example.lessonEnglish.security.service.UserDetailService;

@Configuration
@SuppressWarnings("deprecation")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(10);
	}
	@Order(1)
	@Configuration
	public static class AdminSecurity extends WebSecurityConfigurerAdapter {

		@Autowired
		private JwtFillter jwtFilter;

		@Override
		@Bean
		protected AuthenticationManager authenticationManager() throws Exception {
			// TODO Auto-generated method stub
			return super.authenticationManager();
		}
		@Autowired
		private UserDetailService userDetailService;

		@Autowired
		private PasswordEncoder encoder;

		

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailService).passwordEncoder(encoder);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
					.antMatchers("/api/v1/user/signin").permitAll().antMatchers("/api/v1/dlFileEntry/viewImage/**")
					.permitAll().antMatchers("/api/v1/logo/view/**").permitAll()
					.antMatchers("/api/v1/user/informationResetPassword").permitAll().and().antMatcher("/api/v1/**")
					.authorizeRequests().anyRequest().authenticated();
			http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		}
	}

	@Order(2)
	@Configuration
	public static class CustomerSecurity extends WebSecurityConfigurerAdapter {
		@Autowired
		private PasswordEncoder encoder;

		@Autowired
		private CustomerServiceImpl customerDetailSerivce;
		
		
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(customerDetailSerivce).passwordEncoder(encoder);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
					.antMatchers("/api/v2/customer/signin").permitAll()
					.antMatchers("/api/v2/customer/insert").permitAll()
					.antMatchers("/api/v2/customer/informationResetPassword").permitAll()
					.antMatchers("/api/v2/verification/**").permitAll()
					.and().antMatcher("/api/v2/**")
					.authorizeRequests().anyRequest().authenticated().and()
					.httpBasic();
		}

	}

}
