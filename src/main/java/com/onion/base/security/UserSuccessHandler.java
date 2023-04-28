package com.onion.base.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.onion.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.error("============= Login Success to run ============");
		log.error("============= {} ============", authentication);
		
		//foward
//		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
//		view.forward(request, response);
		
		//redirect
		String remember = (String)request.getParameter("remember");
		if(remember != null && remember.equals("remember")) {
			MemberVO memberVO = (MemberVO)authentication.getPrincipal();
			Cookie cookie = new Cookie("remember", memberVO.getUsername());
			cookie.setMaxAge(60*60);
			response.addCookie(cookie);
		}
		else {
			Cookie [] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("remember")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					break;
				}
			}
		}
		
		

		response.sendRedirect("/")
		
		;
		
	}
	
}
