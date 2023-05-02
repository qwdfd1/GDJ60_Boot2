package com.onion.base.security;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.onion.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserLogoutHandler implements LogoutHandler {
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminKey;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub

		this.simpleLogout();
		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	private void logoutAll() {
//		//카카오계정과 함께 로그아웃
//		
//		//1. 요청 준비
//		RestTemplate restTemplate = new RestTemplate();
//		
//		//2. Header
//		
//		//3. parameter
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("client_id", restKey);
//		params.add("logout_redirect_uri", redirectUri);
//		log.error("============ Logout Result : {} ===========", restKey);
//		log.error("============ Logout Result : {} ===========", redirectUri);
//		
//		//4.
//		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, null);
//		
//		
//		//5. 요청 발생
////		String response = restTemplate.getForObject("https://kauth.kakao.com/oauth/logout", String.class, request);
//		ResponseEntity<String> response = restTemplate.getForEntity("https://kauth.kakao.com/oauth/logout?client_id=" + restKey + "&logout_redirect_uri=http://localhost/member/socialLogout", String.class, request);
//		String result = response.getBody();
//		log.error("============ Logout Result : {} ===========", result);
//		
//		
//		
//		
//		
//	}
	
	private void simpleLogout() {
		RestTemplate restTemplate = new RestTemplate();
		
		MemberVO memberVO = (MemberVO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.error("============ USERNAME : {} ===========", memberVO.getAttributes().get("id"));
		 
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK "+ adminKey);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		//parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("target_id_type", "user_id");
		params.add("target_id", memberVO.getAttributes().get("id").toString());
		
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String,String>>(params, headers);
		
		String id = restTemplate.postForObject("https://kapi.kakao.com/v1/user/logout", req, String.class);
		log.error("============ Log Out Result : {} ===========", id);
	}

	
}
