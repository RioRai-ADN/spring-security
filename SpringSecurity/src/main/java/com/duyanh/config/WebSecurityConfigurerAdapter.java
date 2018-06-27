package com.duyanh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurerAdapter extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	
	/*
	 * PasswordEncoder co implementation la BCryptPasswordEncoder
	 * giup ma hoa mat khau bang thuat toan BCrypt. Nhung de su dung
	 * duoc PasswordEncoder, ta phai cau hinh no len thanh mot bean.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	/**
	 * Cau hinh cac chi tiet bao mat:
	 * +antMatchers(url): khai bao duong dan
	 * +hasRole(roleName): cho phep user co GrantedAuthority
	 * 					la roleName moi duoc truy cap
	 * +permitAll(): cho phep tat ca user truy cap
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/register").permitAll().antMatchers("/").hasRole("MEMBER").antMatchers("/admin").hasRole("admin").and().formLogin().loginPage("/login").usernameParameter("email").passwordParameter("password").defaultSuccessUrl("/").failureUrl("/login?error").and().exceptionHandling().accessDeniedPage("/403");
	}
}
