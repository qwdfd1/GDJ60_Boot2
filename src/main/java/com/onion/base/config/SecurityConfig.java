package com.onion.base.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.onion.base.member.MemberService;
import com.onion.base.member.MemberSocialService;
import com.onion.base.security.UserLoginFailureHandler;
import com.onion.base.security.UserLogoutHandler;
import com.onion.base.security.UserLogoutSuccessHandler;
import com.onion.base.security.UserSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private MemberSocialService memberSocialService;
	
	@Autowired
	private UserLogoutHandler userLogoutHandler;
	
	@Autowired
	private UserLogoutSuccessHandler userLogoutSuccessHandler;

	
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
//				.disable()
				.and()
				.csrf()
				.disable()
			.authorizeRequests()
				//URL과 권한 매칭
				.antMatchers("/").permitAll()
				.antMatchers("/member/*").permitAll()
				.antMatchers("/member/admin/**").hasRole("ADMIN")
				.antMatchers("/notice/add").hasRole("MEMBER")
				.antMatchers("/notice/update").hasRole("ADMIN")
				.antMatchers("/notice/delete").hasRole("ADMIN")
				.antMatchers("/notice/*").permitAll()				
				// 멤버등급 테이블구조에서 회원이 하나의 ROLE만 가지고 있을 때 hasAnyRole 메서드 사용
				.antMatchers("/qna/add").hasAnyRole("ADMIN", "MANAGER", "MEMBER")
				.anyRequest().permitAll()
				.and()
			.formLogin()
				.loginPage("/member/login")
				//.defaultSuccessUrl("/")
				.successHandler(new UserSuccessHandler())
//				.failureUrl("/member/login")
				.failureHandler(new UserLoginFailureHandler())
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/member/logout")
//				.logoutSuccessUrl("/")
				.addLogoutHandler(userLogoutHandler)
				.logoutSuccessHandler(userLogoutSuccessHandler)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll()
				.and()
				.oauth2Login()
				.userInfoEndpoint()
				.userService(memberSocialService)
				;
				
//			.sessionManagement()
//				.maximumSessions(1) // 최대 허용가능한 Session의 갯수
//				.maxSessionsPreventsLogin(false) // false : 이전 사용자 세션 만료, true : 새로운 사용자 인증 실패
		
		return httpSecurity.build();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
