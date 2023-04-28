package com.onion.base.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.onion.base.security.UserLogoutSuccessHandler;
import com.onion.base.security.UserSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Bean
	// public을 선언하면 default로 바꾸라는 메세지 출력
	WebSecurityCustomizer webSecurityConfig() {
		//Security에서 무시해야하는 URL 패턴 등록
		return web -> web
				.ignoring()
				.antMatchers("/images/**")
				.antMatchers("/js/**")
				.antMatchers("/css/**")
				.antMatchers("/favicon/**")
				;
		
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.cors()
				.and()
				.csrf()
				.disable()
				.authorizeRequests()
				//URL과 권한 매칭
				.antMatchers("/").permitAll()
				.antMatchers("/member/join").permitAll()
				.antMatchers("/member/findPassword").permitAll()
				.antMatchers("/member/admin/**").hasRole("ADMIN")
				.antMatchers("/notice/add").hasRole("ADMIN")
				.antMatchers("/notice/update").hasRole("ADMIN")
				.antMatchers("/notice/delete").hasRole("ADMIN")
				.antMatchers("/notice/*").permitAll()				
				// 멤버등급 테이블구조에서 회원이 하나의 ROLE만 가지고 있을 때 hasAnyRole 메서드 사용
				.antMatchers("/qna/add").hasAnyRole("ADMIN", "MANAGER", "MEMBER")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/member/login")
				//.defaultSuccessUrl("/")
				.successHandler(new UserSuccessHandler())
				.failureUrl("/member/login")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/member/logout")
//				.logoutSuccessUrl("/")
				.logoutSuccessHandler(new UserLogoutSuccessHandler())
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll()
				;
				
		return httpSecurity.build();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
